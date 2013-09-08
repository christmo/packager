/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.xplook.util.XplookConstants;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Esta es la clase de carga util aquí se enviará toda la información recuperada
 * o enviada de un lado a otro a través del paquete
 *
 * @author christmo
 * @version 1.0, 05/Sep/2013
 */
public class XplookPacketData implements Serializable {

//    private int numberChild;
    private String id;
    private String database;
    private String collection;
    private Map<String, Object> request;
    private LinkedList<XplookRow> response;

    /**
     * Contructor por defecto
     */
    public XplookPacketData() {
    }

    /**
     * Obtiene el id unico del registro
     *
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * Setea el id unico del registro
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene la Base de datos don se almacena la informacion
     *
     * @return database -> nombre de la base de datos
     */
    public String getDataBase() {
        return database;
    }

    /**
     * Setea la base de datos donde se debe guardar la información
     *
     * @param db Nombre de la base de datos
     */
    public void setDataBase(String db) {
        this.database = db;
    }

    /**
     * Obtiene la coleccion, archivo o tabla en la que persiste la informacion
     *
     * @return Obtiene el nombre de la colección
     */
    public String getCollection() {
        return collection;
    }

    /**
     * Setea la coleccion, archivo o tabla en la que se persistira la
     * informacion
     *
     * @param collection
     */
    public void setCollection(String collection) {
        this.collection = collection;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Obtiene una fila del resultado obtenido
     *
     * @param rowId
     * @return Una fila determinada por un ID
     */
    public XplookRow getRow(final Object rowId) {
        try {
            return Iterables.find(getResponse(), new Predicate<XplookRow>() {
                public boolean apply(XplookRow row) {
                    return row.getRowId().equals(rowId);
                }
            });
        } catch (NoSuchElementException ex) {
            throw new NoSuchElementException("Not present row[" + rowId + "] into XplookPack");
        }
    }

    /**
     * Obtiene un valor de todos los resultados obtenidos, en formato de fila y
     * columna una sola celda
     *
     * @param row Identificador de la fila
     * @param column Identificador de la columna
     * @return Una celda de las filas y las columas recuperadas
     */
    public Object getCell(Object row, String column) {
        if (containsRow(row)) {
            if (getRow(row).containsColumn(column)) {
                return getRow(row).getColumn(column);
            } else {
                throw new IllegalArgumentException("Row [" + row + "] not contains colunm [" + column + "]");
            }
        } else {
            throw new IllegalArgumentException("Response no contains row [" + row + "]");
        }
    }

    /**
     * Obtiene todos los parametros de la solicitud
     *
     * @return Mapa de todos los parametros de solicitud
     */
    public Map<String, Object> getRequest() {
        if (this.request == null) {
            request = new HashMap<String, Object>();
        }
        return request;
    }

    /**
     * Setea todo el mapa de parámetros de la solicitud
     *
     * @param request Mapa de parámetros
     */
    public void setRequest(Map<String, Object> request) {
        if (request != null) {
            this.request = request;
        }
    }

    /**
     * Setea un parámetro a la solicitud, a través de estos se hará la búsqueda
     * de datos en el repositorio de información
     *
     * @param key Nombre del campo
     * @param value valor del campo
     */
    public void setRequest(String key, Object value) {
        this.getRequest().put(key, value);
    }

    /**
     * Agrega un parametro clave = valor a las solicitudes
     *
     * @param key nombre del campo
     * @param value valor del campo
     */
    public void addRequest(String key, Object value) {
        this.getRequest().put(key, value);
    }

    /**
     * Obtiene la lista de parámetros de la solicitud
     *
     * @return Lista de parámetros
     */
    public LinkedList<XplookRow> getResponse() {
        if (this.response == null) {
            response = new LinkedList<XplookRow>();
        }
        return response;
    }

    /**
     * Setea una lista de filas de respuesta obtenidas de la consulta al
     * repositorio de información
     *
     * @param response Lista de filas consultadas
     */
    public void setResponse(LinkedList<XplookRow> response) {
        this.response = response;
    }

    /**
     * Agrega un campo mas a Response dentro de una fila comodin con row = 0
     *
     * @param key
     * @param value
     */
    public void addResponse(String key, Object value) {
        if (containsRow(XplookConstants.ROW_DEFAULT)) {
            Iterables.getOnlyElement(getResponse());
            getRow(XplookConstants.ROW_DEFAULT).addColumn(key, value);
        } else {
            XplookColumn column = new XplookColumn(key, value);
            XplookRow row = new XplookRow(XplookConstants.ROW_DEFAULT, column);
            getResponse().add(row);
        }
    }

    /**
     * Comprueba si en los datos existe la fila con ese identificador
     *
     * @param rowId
     * @return boolean
     */
    public boolean containsRow(final Object rowId) {
        return Iterables.any(getResponse(), new Predicate<XplookRow>() {
            public boolean apply(XplookRow row) {
                return row.getRowId().equals(rowId);
            }
        });
    }

    /**
     * Agrega valores a la fila = 0 comodin para cuando solo se consulta un
     * registro
     *
     * @param fields campo para agregar a la fila de respuesta
     */
    public void addResponse(XplookColumn... fields) {
        if (response != null) {
            getRow(XplookConstants.ROW_DEFAULT).setColumns(fields);
        }
    }

    /**
     * Agrega una fila a los resultados de la consultas a devolver
     *
     * @param rowId nombre de la fila en la que se insertara en la respuesta
     * @param fields Arreglo de campos que se insertaran en la fila
     */
    public void addResponse(Object rowId, XplookColumn... fields) {
        if (fields != null && rowId != null) {
            XplookRow row = new XplookRow(rowId, fields);
            getResponse().add(row);
        }
    }
}
