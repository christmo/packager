/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import java.io.Serializable;
import java.util.Map;

/**
 * Columna que compone los registros recuperados por Xplook
 * @author christmo
 * @version 1.0, 05/Sep/2013
 */
public class XplookColumn implements Serializable {

    private String key;
    private Object value;
    private Map<String, Object> metadata;

    /**
     * constructor para construir una columna de un multiregistro
     * @param key el nombre del campo en la columna
     * @param value el valor del campo en la columna
     */
    public XplookColumn(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    /**
     * constructor de una columna de un multiregistro con metadata
     * @param key el nombre del campo en la columna
     * @param value el valor del campo en la columna
     * @param metadata metadata adicional clave, valor del campo
     */
    public XplookColumn(String key, Object value, Map<String, Object> metadata) {
        this.key = key;
        this.value = value;
        this.metadata = metadata;
    }

    /**
     * Obtiene la clave del campo
     * @return Nombre de la clave
     */
    public String getKey() {
        return key;
    }

    /**
     * Setea la clave del campo
     * @param key Nombre del campo
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * Obtiene el valor del campo
     * @return valor del campo
     */
    public Object getValue() {
        return value;
    }

    /**
     * Setea el valor del campo
     * @param value valor del campo
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Setea metadata del campo
     * @return Mapa clave, valor de la metadata
     */
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    /**
     * Setea metadata al campo
     * @param metadata mapa clave, valor de metadata del campo
     */
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}
