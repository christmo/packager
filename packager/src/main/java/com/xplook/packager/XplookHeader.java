/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.xplook.util.ActionType;
import com.xplook.util.Device;
import com.xplook.util.Mode;
import com.xplook.util.ObjectType;
import java.io.Serializable;

/**
 * Clase utilizada para contener la información del HEADER del paquete, 
 * tiene la infomación necesaria para realizar una determinada accion con el
 * paquete, entre la infomación que se solicita está:
 * <p>
 * <ul>
 * <li>Código del usuario del cual se debe obtener la información 
 * <li>{@link Mode}Modo en el que está el paquete puede ser GET,POST,UPDATE,DELETE
 * <li>{@link ObjectType}Tipo de Objeto determina si el paquete tiene una lista 
 * de resltados o contiene un solo resultado, opciones son LIST o ITEM
 * <li>Código Padre permite determinar que paquete fue el que originó al paquete
 * actual
 * <li>Lenguaje permite determinar el idioma en el que se debe recuperar la 
 * información esta debe ser utilizada mediante el estándar ISO639
 * <li>{@link Device} Dispositivo permite determinar a que tipo de dispositivo 
 * va dirigida la informacion para determinar el tratamiento
 * <li>{@link ActionType} Tipo de Acción determina si se debe obtener solo
 * información de la entidad principal, o también de sus relaciones, las opciones
 * son SHORT y DETAIL
 * <li>apiKey se utilizará para determinar cuanto utiliza una aplicacion el motor
 * </ul>
 *
 * @author christmo
 * @version 1.0, 1/Sep/2013
 */
public class XplookHeader implements Serializable {

    private String idUser;
    private String apiKey;
    private Mode mode;
    private ActionType actionType;
    private ObjectType objectType;
    private String idParent;
    private String language;
    private Device device;
    private String engineDB;

    /**
     * Constructor por default para crear un header limpio
     */
    public XplookHeader() {
    }

    /**
     * @return the idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * @param idUser the idUser to set
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey the apiKey to set
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return the mode
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * @param mode the mode to set
     */
    public void setMode(Mode mode) {
        this.mode = mode;
    }

    /**
     * @return the actionType
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * @param actionType the actionType to set
     */
    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    /**
     * @return the objectType
     */
    public ObjectType getObjectType() {
        return objectType;
    }

    /**
     * 
     * @param objectType the objectType to set
     */
    public void setObjectType(ObjectType objectType) {
        this.objectType = objectType;
    }

    /**
     * Obtiene el codigo del paquete padre del paquete actual
     * @return the idParent
     */
    public String getIdParent() {
        return idParent;
    }

    /**
     * Asigna el codigo del paquete padre del paquete actual
     * @param idParent the idParent to set
     */
    public void setIdParent(String idParent) {
        this.idParent = idParent;
    }

    /**
     * Obtiene el lenguaje en el que se tiene que mostrar el paquete
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * Asigna el lenguaje en el que se tiene que mostar el paquete
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * Obtiene el dispositivo en el que se debe mostrar los datos
     *
     * @return
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Asigna el dispositivo que se visualizaran los datos
     *
     * @param device
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    /**
     * @return the engineDB
     */
    public String getEngineDB() {
        return engineDB;
    }

    /**
     * @param engineDB the engineDB to set
     */
    public void setEngineDB(String engineDB) {
        this.engineDB = engineDB;
    }
}
