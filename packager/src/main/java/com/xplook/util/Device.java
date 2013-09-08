/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.util;

/**
 * Dispositivo permite determinar el destino que tendrá la información, con ello
 * se se ha determinado por ahora los siguientes tipos:
 * <ul>
 * <il>MOBILE dispositivos móviles
 * <il>WEB Monitor de PC, TV, LCD, etc.
 * <il>MOBILE_WEB la información debe estar disponible en cualquier dispositivo
 * <il>NO_DISPLAY información que no debe ser presentada en ningún dispositivo
 * </ul>
 *
 * @author christmo
 * @version 1.0, 1/Sep/2013
 */
public enum Device {

    /**
     * dispositivos móviles
     */
    MOBILE,
    /**
     * Monitor de PC, TV, LCD, etc.
     */
    WEB,
    /**
     * la información debe estar disponible en cualquier dispositivo
     */
    MOBILE_WEB,
    /**
     * información que no debe ser presentada en ningún dispositivo
     */
    NO_DISPLAY
}
