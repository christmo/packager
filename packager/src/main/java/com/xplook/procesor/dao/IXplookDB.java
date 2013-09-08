/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.procesor.dao;

/**
 * Interfaz para tener la referencia de metodos que se puede aplicar sobre una
 * base de datos y para el funcionamiento genérico de busqueda de información en
 * los distintos repositorios de información que pueden ser SQL o NoSQL
 *
 * @author christmo
 * @version 1.0, 08/Sep/
 * @013
 *
 * @param <T> Genérico, el cual permite utilizar cualquier clase de paquete para
 * obtener información y responder en el mismo formato.
 */
public abstract class IXplookDB<T> {

    /**
     * Obtener conexion a la base de datos
     *
     * @param pack
     * @throws Exception
     */
    public abstract void getConection(T pack) throws Exception;

    /**
     * Insertar pack en la base de datos
     * @param pack Paquete con la información a ser insertada
     * @return Se retornará valores en la misma estructura de entrada.
     */
    public abstract T insert(T pack);

    /**
     * Actualiza la informacion de un registro a partir de su ID
     *
     *@param pack Paquete con la información a ser actualizada
     * @return Se retornará valores en la misma estructura de entrada.
     */
    public abstract T update(T pack);

    /**
     * Eliminar un registro en base al ID enviado en el mensaje
     *
     * @param pack Paquete con la información a ser eliminada
     * @return Se retornará valores en la misma estructura de entrada.
     */
    public abstract T delete(T pack);

    /**
     * Elimina los registros que corresponden a la clave y el valor que vengan
     * en el request del paquete
     *
     * @param pack Paquete con la información a ser eliminada
     * @return Se retornará valores en la misma estructura de entrada.
     */
    public abstract T deleteByKeyValue(T pack);

    /**
     * Buscar en la base de datos por una clave
     *
     * @param key
     * @param value
     * @return T
     */
    // public abstract T findByKeyValue(String key, Object value);
    /**
     * Busca todas las coincidencias que vengan dentro de la solicitud clave y
     * valor igual dentro de una coleccion
     *
     * @param pack
     * @return XplookPacket
     */
    public abstract T findByKeyValue(T pack);

    /**
     * Busca por el _id de mongo del registro que se quiere obtener pobla la
     * metada del XplookPacket ingresado
     *
     * @param pack
     * @return XplookPacket
     */
    public abstract T findById(T pack);
}
