/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.util;

/**
 * Determina si al recuperar la información se debe traer todas sus relaciones o
 * simplemente la información principal:
 *
 * <ul>
 * <il>SHORT solo información de la entidad principal
 * <il>DETAIL información de entidad principal y sus relaciones
 * </ul>
 *
 * @author christmo
 * @version 1.0, 1/Sep/2013
 */
public enum ActionType {

    SHORT, DETAIL
}
