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
import com.xplook.util.ErrorType;
import com.xplook.util.HiderFieldsPacket;
import java.io.Serializable;
import java.util.IllegalFormatConversionException;
import java.util.LinkedList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.SerializationUtils;

/**
 * Clase principal para el uso de los paquetes
 * <code>Xplook</code>, esta debe ser instanaciada para crear un paquete de tipo
 * json que puede ser enviado a través de los metodos, dentro de este se puede
 * enviar inforamción de cualquier tipo, la cual lo convierte al
 * <code>XplookPacket</code> en una estructura dinámica.
 * <p/>
 * Está compuesto por 3 secciones:
 * <ul>
 * <li><code>{@link XplookHeader}</code> Información General del Paquete
 * <li><code>{@link XplookPacketData}</code> Carga util del paquete
 * <li><code>{@link XplookError}</code> Errores a mostrar
 * </ul>
 *
 * @author christmo
 * @version 1.0, 1/Sep/2013
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
        if (header != null) {
            this.setHeader(header);
        }
        if (packetData != null) {
            this.getPacketData().add(packetData);
        }
        if (error != null) {
            this.addError(error);
        }
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
//    public void addError(XplookError error) {
//        if (error != null) {
//            getError().add(error);
//        }
//    }
//    public void addError(Object errorCode, Throwable exception) {
//        getError().add(new XplookError(errorCode, exception));
//    }
    
    /**
     * Agregar mensaje ERROR al paquete con un código de error y paramaetros
     *
     * @param errorCode Código de mensaje de warning
     * @param parameters Parámetros
     */
    public void addError(Object errorCode, Object... parameters) {
        try {
            getError().add(new XplookError(ErrorType.ERROR, getHeader(), errorCode, parameters));
        } catch (IllegalFormatConversionException ex) {
            getError().add(new XplookError(ErrorType.ERROR, getHeader(), ex));
        }
    }

    /**
     * Agregar mensaje WARN al paquete con un código de error y paramaetros
     *
     * @param errorCode Código de mensaje de warning
     * @param parameters Parámetros
     */
    public void addWarn(Object errorCode, Object... parameters) {
        try {
            getError().add(new XplookError(ErrorType.WARN, getHeader(), errorCode, parameters));
        } catch (IllegalFormatConversionException ex) {
            getError().add(new XplookError(ErrorType.ERROR, getHeader(), ex));
        }
    }

    /**
     * Agregar un mensaje INFO de información al paquete
     *
     * @param errorCode Código de Mensaje de Informacion
     * @param parameters Parámetros
     */
    public void addInfo(Object errorCode, Object... parameters) {
        try {
            getError().add(new XplookError(ErrorType.INFO, getHeader(), errorCode, parameters));
        } catch (IllegalFormatConversionException ex) {
            getError().add(new XplookError(ErrorType.ERROR, getHeader(), ex));
        }
    }

    /**
     * Agrega un mensaje ERROR al paquete, dependiendo de la excepción
     * generada
     *
     * @param exception Excepción generada por el código, dependiendo de ella se
     * presentará el mensaje parametrizado para esta
     * @param parameters Parámetros que se cargaran en el mensaje a visualizar
     */
    public void addError(Throwable exception, Object... parameters) {
//        try {
        getError().add(new XplookError(ErrorType.ERROR, getHeader(), exception, parameters));
//        } catch (IllegalFormatConversionException ex) {
//            getError().add(new XplookError(ErrorType.ERROR, getHeader(), ex));
//        }
    }

    /**
     * Agregar mensaje WARN cuando se de una excepción en el código
     *
     * @param exception Excepción generada por el código, dependiendo de ella se
     * presentará el mensaje parametrizado para esta
     * @param parameters Parámetros que se cargaran en el mensaje a visualizar
     */
    public void addWarn(Throwable exception, Object... parameters) {
//        try {
        getError().add(new XplookError(ErrorType.WARN, getHeader(), exception, parameters));
//        } catch (IllegalFormatConversionException ex) {
//            getError().add(new XplookError(ErrorType.ERROR, getHeader(), ex));
//        }
    }

    /**
     * Agregar mensaje de INFO cuando se de una excepción en el código
     *
     * @param exception Excepción generada por el código, dependiendo de ella se
     * presentará el mensaje parametrizado para esta
     * @param parameters Parámetros que se cargaran en el mensaje a visualizar
     */
    public void addInfo(Throwable exception, Object... parameters) {
//        try {
        getError().add(new XplookError(ErrorType.INFO, getHeader(), exception, parameters));
//        } catch (IllegalFormatConversionException ex) {
//            getError().add(new XplookError(ErrorType.ERROR, getHeader(), ex));
//        }
    }

//    public void addError(Object errorCode, String desc) {
//        if (desc != null) {
//            getError().add(new XplookError(errorCode, desc));
//        }
//    }
    /**
     * Sobre escribe los errores del paquete
     *
     * @param error
     */
    public void setAllErrors(LinkedList<XplookError> error) {
        if (error != null) {
            this.error = error;
        }
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

    /**
     * Obtine los datos de la lista de contenido en una determinada posición
     *
     * @param index número de fila donde se encuenta la data a recuperar
     * @return XplookPacketData de la lista de carga utiil del paquete
     * @throws IndexOutOfBoundsException
     */
    public XplookPacketData getPacketData(int index) throws IndexOutOfBoundsException {
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

//    /**
//     * Reemplaza los parametros de la seccion de errores para presentar el
//     * mensaje final de error completamente armado
//     *
//     * @param json
//     * @return JsonElement
//     * @throws CloneNotSupportedException
//     */
//    private JsonElement replaceErrorParameters(Gson json) throws CloneNotSupportedException {
//        JsonElement jsonElement = json.toJsonTree(this.clone());
//        if (jsonElement.getAsJsonObject().getAsJsonArray("error") != null) {
//            try {
//                for (JsonElement errorIndex : jsonElement.getAsJsonObject().getAsJsonArray("error")) {
//                    JsonObject errorSection = errorIndex.getAsJsonObject();
//                    JsonObject param = errorSection.getAsJsonObject("parameters");
//                    for (Map.Entry<String, JsonElement> entry : param.entrySet()) {
//                        String desc = errorSection.get("description").getAsString();
//                        errorSection.addProperty("description", desc.replace("-=" + entry.getKey() + "=-", entry.getValue().getAsString()));
//                    }
//                }
//            } catch (IllegalStateException ex) {
//                //No hacer nada cuando no se tenga parametros
//            }
//        }
//
//        return jsonElement;
//    }
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
        Gson gson = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
//        Gson gson = new GsonBuilder().create();
//        Gson gson = new GsonBuilder().setExclusionStrategies(new HiderFieldsPacket(this)).setPrettyPrinting().create();
        JsonElement jsonElement = null;
        try {
            jsonElement = gson.toJsonTree(this.clone());
//            jsonElement = replaceErrorParameters(gson);
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(XplookPacket.class.getName()).log(Level.SEVERE, null, ex);
        }

        return gson.toJson(jsonElement);
    }
}
