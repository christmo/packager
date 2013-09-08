/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.util;

/**
 * Tipo de Objeto permite describir si el paquete trae una lista de objetos o
 * uno solo.
 *
 * <ul>
 * <il>LIST Lista de resultados
 * <il>ITEM Un solo resultado
 * </ul>
 *
 * @author christmo
 * @version 1.0, 1/Sep/2013
 */
public enum ObjectType {

    /**
     * Lista de resultados
     */
    LIST,
    /**
     * Un solo resultado
     */
    ITEM
}