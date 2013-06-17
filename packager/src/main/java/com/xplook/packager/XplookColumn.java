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
 */
public class XplookColumn implements Serializable {

    private String key;
    private Object value;
    private Map<String, Object> metadata;

    public XplookColumn(String key, Object value) {
        this.key = key;
        this.value = value;
    }

    public XplookColumn(String key, Object value, Map<String, Object> metadata) {
        this.key = key;
        this.value = value;
        this.metadata = metadata;
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * @return the metadata
     */
    public Map<String, Object> getMetadata() {
        return metadata;
    }

    /**
     * @param metadata the metadata to set
     */
    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}
