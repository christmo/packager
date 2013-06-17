/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xplook.util.HiderFieldsPacket;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.SerializationUtils;

/**
 *
 * @author christmo
 */
public final class XplookPacket implements Serializable, Cloneable {

    private XplookHeader header;
    private LinkedList<XplookPacketData> packetData;
    private LinkedList<XplookError> error;

    /**
     * Paquete Xplook
     *
     * @param header
     * @param packetData
     * @param error
     */
    public XplookPacket(XplookHeader header, XplookPacketData packetData, XplookError error) {
        this.setHeader(header);
        if (packetData != null) {
            this.getPacketData().add(packetData);
        }
        this.addError(error);
    }

    /**
     * Crea un paquete solo con datos sin cabecera ni errores
     *
     * @param packetData
     */
    public XplookPacket(XplookPacketData packetData) {
        this(null, packetData, null);
    }

    /**
     * Crear paquete Full
     *
     * @param header
     * @param packetData
     * @param error
     */
    private XplookPacket(XplookHeader header, LinkedList<XplookPacketData> packetData, LinkedList<XplookError> error) {
        this.setHeader(header);
        this.setPacketData(packetData);
        this.setAllErrors(error);
    }

    /**
     * Obtiene los valores del Header
     *
     * @return XplookHeader
     */
    public XplookHeader getHeader() {
        return header;
    }

    /**
     * Setea los valores del Header
     *
     * @param header
     */
    public void setHeader(XplookHeader header) {
        if (header != null) {
            this.header = header;
        }
    }

    /**
     * Obtiene la lista de Errores del paquete
     *
     * @return LinkedList<XplookError>
     */
    public LinkedList<XplookError> getError() {
        if (this.error == null) {
            this.error = new LinkedList<XplookError>();
        }
        return error;
    }

    /**
     * Agrega un nuevo error a la lista de Errores del Paquete, no se permite
     * poner null ese valor sera omitido
     *
     * @param error
     */
    public void addError(XplookError error) {
        if (error != null) {
            getError().add(error);
        }
    }

    public void addError(Object errorCode, String desc) {
        if (desc != null) {
            getError().add(new XplookError(errorCode, desc));
        }
    }

    /**
     * Sobre escribe los errores del paquete
     *
     * @param error
     */
    public void setAllErrors(LinkedList<XplookError> error) {
        this.error = error;
    }

    /**
     * @return the packetData
     */
    public LinkedList<XplookPacketData> getPacketData() {
        if (packetData == null) {
            this.packetData = new LinkedList<XplookPacketData>();
        }
        return packetData;
    }

    public XplookPacketData getPacketData(int index) {
        return getPacketData().get(index);
    }

    /**
     * Setea la Informacion del paquete, no se pemite setear null
     *
     * @param packetData
     */
    public void setPacketData(LinkedList<XplookPacketData> packetData) {
        if (packetData != null) {
            this.packetData = packetData;
        }
    }

    /**
     * Agrega una nueva seccion de datos al paquete, no se permite insertar null
     * este valor es omitido
     *
     * @param packetData
     */
    public void addPacketData(XplookPacketData packetData) {
        if (packetData != null) {
            this.packetData.add(packetData);
        }
    }

    /**
     * Reemplaza los parametros de la seccion de errores para presentar el
     * mensaje final de error completamente armado
     *
     * @param json
     * @return JsonElement
     * @throws CloneNotSupportedException
     */
    private JsonElement replaceErrorParameters(Gson json) throws CloneNotSupportedException {
        JsonElement jsonElement = json.toJsonTree(this.clone());
        if (jsonElement.getAsJsonObject().getAsJsonArray("error") != null) {
            try {
                for (JsonElement errorIndex : jsonElement.getAsJsonObject().getAsJsonArray("error")) {
                    JsonObject errorSection = errorIndex.getAsJsonObject();
                    JsonObject param = errorSection.getAsJsonObject("parameters");
                    for (Map.Entry<String, JsonElement> entry : param.entrySet()) {
                        String desc = errorSection.get("description").getAsString();
                        errorSection.addProperty("description", desc.replace("-=" + entry.getKey() + "=-", entry.getValue().getAsString()));
                    }
                }
            } catch (IllegalStateException ex) {
                //No hacer nada cuando no se tenga parametros
            }
        }

        return jsonElement;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return SerializationUtils.clone(this);
    }

    @Override
    public String toString() {
//        Gson gson = new Gson();
//        Gson gson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
//            public boolean shouldSkipField(FieldAttributes f) {
//                System.out.println("excl>" + f.getName());
//                return f.getName().toLowerCase().contains("response");
//            }
//
//            public boolean shouldSkipClass(Class<?> clazz) {
//                return false;
//            }
//        }).setPrettyPrinting().create();
        Gson gson = new GsonBuilder().setExclusionStrategies(new HiderFieldsPacket(this)).setPrettyPrinting().create();
        JsonElement jsonElement = null;
        try {
            jsonElement = replaceErrorParameters(gson);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(XplookPacket.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gson.toJson(jsonElement);
    }
}
