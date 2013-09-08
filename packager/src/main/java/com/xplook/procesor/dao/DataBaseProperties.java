/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.procesor.dao;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author christmo
 */
public class DataBaseProperties {

    private String key;
    private Map<String, Object> properties;

    public DataBaseProperties(String key) {
        this.key = key;
        properties = new HashMap<String, Object>();
    }
    
    public void addProperty(String key, Object value){
        properties.put(key, value);
    }
    
    public Object getProperty(String key){
        return properties.get(key);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }
}
