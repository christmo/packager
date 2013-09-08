/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.util;

/**
 * Modos en los que puede estar el paquete, estos pueden ser:
 * <ul>
 * <li>GET Recuperar información, sería el modo que se debe enviar el paquete
 * para que obtenga infomración, del repositorio de datos.
 * <li>POST Crear información, este modo permite guardar o insertar datos dentro
 * del repositorio utilizado.
 * <li>UPDATE Actualizar determinada información en el repositorio que se
 * almacenan los datos
 * <li>DELETE Eliminar determinada información del repositorio de datos
 * </ul>
 *
 * @author christmo
 * @version 1.0, 1/Sep/2013
 */
public enum Mode {

    /**
     * Recuperar información, sería el modo que se debe enviar el paquete para
     * que obtenga infomración, del repositorio de datos.
     */
    GET,
    /**
     * Crear información, este modo permite guardar o insertar datos dentro del
     * repositorio utilizado.
     */
    POST,
    /**
     * Actualizar determinada información en el repositorio que se almacenan los
     * datos
     */
    UPDATE,
    /**
     * Eliminar determinada información del repositorio de datos
     */
    DELETE
}
//enum Mode {
//
//    PULL, PUSH
//}
