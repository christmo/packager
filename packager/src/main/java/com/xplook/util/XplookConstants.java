/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.util;

/**
 *
 * @author christmo
 */
public class XplookConstants {

    /**
     * Nombre de la fila por defecto del paquete de datos cuando los valores de
     * respuesta solo pertenecen a un solo registro
     */
    public static final String ROW_DEFAULT = "0";
    /**
     * Etiqueta XML de configuracion del nombre de la implementacion por defecto
     * del framework
     */
    public static final String TAG_DEFAULT_DB = "default_db_tools";
    /**
     * Etiqueta XML de configuracion del nombre de la base de datos
     */
    public static final String TAG_NAME_SERVICE = "[@name]";
    /**
     * Etiqueta XML de configuracion del tipo de base de datos Ejm: nosql o sql
     */
    public static final String TAG_TYPE_SERVICE = "[@type]";
    /**
     * Etiqueta XML de configuracion que engloba las propiedades de la base de
     * datos
     */
    public static final String TAG_DATABASES = "database";
    /**
     * Etiqueta XML de configuracion de la clase implementada por Xplook para
     * conectarse a una determinada base de datos
     */
    public static final String TAG_CLASS_IMPL = "class_impl";
    /**
     * Etiqueta XML de configuracion del nombre de usuario de la base de datos
     */
    public static final String TAG_USER_DB = "user";
    /**
     * Etiqueta XML de configuracion de la clave del usuario de la base de datos
     */
    public static final String TAG_PASS_DB = "pass";
    /**
     * Etiqueta XML de configuracion del nombre o IP del servidor de la DB
     */
    public static final String TAG_SERVER_DB = "server";
    /**
     * Etiqueta XML de configuracion del puerto en el que esta instalada la BD
     */
    public static final String TAG_PORT_DB = "port";
    /**
     * Etiqueta de configuracion para obtener la conexion a la BD del mapa
     */
    public static final String TAG_CONNECTION_DB = "connection";
    /**
     * Ruta de por defecto del archivo de propiedades de la Base de Datos
     */
    public static final String PATH_PROPERTIES_DB = "/configuration.xml";
}
