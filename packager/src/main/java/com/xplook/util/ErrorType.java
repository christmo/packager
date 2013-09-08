/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.util;

/**
 * Tipos de mensaje que puede contener el XplookPaquet
 *
 * @author christmo
 * @version 1.0, 08/Sep/2013
 */
public enum ErrorType {

    /**
     * Tipo para mensaje de información
     */
    INFO("I"),
    /**
     * Tipo para mensaje de alerta
     */
    WARN("W"),
    /**
     * Tipo para mensaje de error
     */
    ERROR("E");

    /**
     * Constructor por defecto
     */
    private ErrorType() {
    }

    /**
     * Setea el tipo de error
     * @param e Código de mensaje 
     */
    private ErrorType(String e) {
    }
}