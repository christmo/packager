/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Fila retornada por xplook en cada respuesta
 *
 * @author christmo
 * @version 1.0, 05/Sep/2013
 */
public final class XplookRow implements Serializable {

    private Object rowId;
    private LinkedList<XplookColumn> columns;

    /**
     * Crea una Fila con N candidad de columnas dependiendo de las que se envien
     *
     * @param rowId
     * @param columns
     */
    public XplookRow(Object rowId, LinkedList<XplookColumn> columns) {
        this.rowId = rowId;
        this.columns = columns;
    }

    /**
     * Crea una Fila con N candidad de columnas dependiendo de las que se envien
     *
     * @param rowId
     * @param columns
     */
    public XplookRow(Object rowId, XplookColumn... columns) {
        this.rowId = rowId;
        getColumns().addAll(Arrays.asList(columns));
    }

    /**
     * Obtiene el valor de la fila, identificador de cualquier tipo
     * @return objeto que representa el id de la fila
     */
    public Object getRowId() {
        return rowId;
    }

    /**
     * Setea el código de la fila
     * @param rowId Id de la Fila
     */
    public void setRowId(Object rowId) {
        this.rowId = rowId;
    }

    /**
     * Obtiene las columas con los valores de la fila
     * @return lista de las columnas de la fila
     */
    public LinkedList<XplookColumn> getColumns() {
        if (columns == null) {
            columns = new LinkedList<XplookColumn>();
        }
        return columns;
    }

    /**
     * Setea la lista de columnas de la fila
     * @param columns lista de columnas de la fila
     */
    public void setColumns(LinkedList<XplookColumn> columns) {
        if (columns != null) {
            this.columns = columns;
        }
    }

    /**
     * Obtiene una columna de la fila, a partir de su nombre clave
     *
     * @param field nombre clave de la columna
     * @return XplookColumn columna de la fila
     */
    public XplookColumn getColumn(final String field) {
        return Iterables.find(getColumns(), new Predicate<XplookColumn>() {
            public boolean apply(XplookColumn column) {
                return column.getKey().equals(field);
            }
        });
    }

    /**
     * Agrega una columna a la fila, sin metadata
     *
     * @param key nombre de la columna
     * @param value valor de la columna
     */
    public void addColumn(String key, Object value) {
        XplookColumn column = new XplookColumn(key, value);
        getColumns().add(column);
    }

    /**
     * Agrega columnas a la fila
     *
     * @param fields
     */
    public void setColumns(XplookColumn[] fields) {
        getColumns().addAll(Arrays.asList(fields));
    }

    /**
     * Comprueba si la columna existe dentro de esa fila
     *
     * @param columnId
     * @return boolean
     */
    public boolean containsColumn(final String columnId) {
        return Iterables.any(getColumns(), new Predicate<XplookColumn>() {
            public boolean apply(XplookColumn row) {
                return row.getKey().equals(columnId);
            }
        });
    }
}
