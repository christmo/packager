/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.xplook.util.XplookConstants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author christmo
 */
public class XplookPacketData implements Serializable {

//    private int numberChild;
    private String id;
    private String database;
    private String collection;
    private Map<String, Object> request;
    private Map<String, Map<String, Object>> response;

    public XplookPacketData() {
    }
    
    /**
     * Obtiene el id unico del registro 
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Setea el id unico del registro
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene la Base de datos don se almacena la informacion
     * @return database -> nombre de la base de datos
     */
    public String getDataBase() {
        return database;
    }

    /**
     * Setea la base de datos donde se debe guardar la información
     * @param String
     */
    public void setDataBase(String db) {
        this.database = db;
    }

    /**
     * Obtiene la coleccion, archivo o tabla en la que persiste la informacion
     * @return String
     */
    public String getCollection() {
        return collection;
    }

    /**
     * Setea la coleccion, archivo o tabla en la que se persistira la informacion
     * @param collection
     */
    public void setCollection(String collection) {
        this.collection = collection;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Obtiene una fila del resultado obtenido
     *
     * @return the result
     */
    public Map<String, Object> getPayLoad(String row) {
        return getResponse().get(row);
    }

    /**
     * Obtiene un valor de todos los resultados obtenidos
     *
     * @param row
     * @param column
     * @return Object
     */
    public Object getCell(String row, String column) {
        if (getResponse().containsKey(row)) {
            if (getResponse().get(row).containsKey(column)) {
                return getResponse().get(row).get(column);
            } else {
                throw new IllegalArgumentException("Row [" + row + "] not contains colunm [" + column + "]");
            }
        } else {
            throw new IllegalArgumentException("Response no contains row [" + row + "]");
        }
    }

    /**
     * @return the request
     */
    public Map<String, Object> getRequest() {
        if (this.request == null) {
            request = new HashMap<String, Object>();
        }
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(Map<String, Object> request) {
        if (request != null) {
            this.request = request;
        }
    }

    /**
     * Agrega un parametro clave = valor a las solicitudes
     *
     * @param key
     * @param value
     */
    public void addRequest(String key, Object value) {
        this.getRequest().put(key, value);
    }

    /**
     * @return the response
     */
    public Map<String, Map<String, Object>> getResponse() {
        if (this.response == null) {
            response = new HashMap<String, Map<String, Object>>();
        }
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(Map<String, Map<String, Object>> response) {
        this.response = response;
    }

    /**
     * Agrega un campo mas a Response dentro de una fila comodin con row = 0
     *
     * @param key
     * @param value
     */
    public void addResponse(String key, Object value) {
        if (getResponse().containsKey(XplookConstants.ROW_DEFAULT)) {
            getResponse().get(XplookConstants.ROW_DEFAULT).put(key, value);
        } else {
            Map<String, Object> fields = new HashMap<String, Object>();
            fields.put(key, value);
            getResponse().put(XplookConstants.ROW_DEFAULT, fields);
        }
    }

    /**
     * Agrega valores a la fila = 0 comodin para cuando solo se consulta un
     * registro
     *
     * @param response
     */
    public void addResponse(Map response) {
        if (response != null) {
            getResponse().put(XplookConstants.ROW_DEFAULT, response);
        }
    }

    /**
     * Agrega una fila a los resultados de la consultas a devolver
     *
     * @param row
     * @param response
     */
    public void addResponse(String row, Map response) {
        if (response != null && row != null) {
            getResponse().put(row, response);
        }
    }
}
