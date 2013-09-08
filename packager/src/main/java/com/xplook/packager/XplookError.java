/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.google.common.base.CharMatcher;
import com.xplook.procesor.dao.IXplookDB;
import com.xplook.procesor.dao.impl.XplookMongoImpl;
import com.xplook.util.ErrorType;
import java.io.Serializable;
import java.util.IllegalFormatConversionException;

/**
 * Sección de error del paquete, en esta sección se agregaran los errores
 * ocurridos durante la ejecución del paquete, con esto se puede mostrar al
 * usuario de manera legible para tomar una determinada acción.
 * <p>
 * El mensaje se debe estructurar de la siguiente forma para que sea incluido de
 * forma correcta en el paquete.
 * <p>
 * <b>entrada:</b> [0] -> prueba<br/>
 * <code>Mensaje de -=1=-</code>
 * <p>
 * <b>salida:</b>
 * <code>Mensaje de prueba</code>
 *
 * <ul>
 * <il> Código de Error este puede ser de cualquier tipo y dependerá de como se
 * organice la codificación de los errores
 * <il>Descripción con esta se puede poner la descripción que se debe visualizar
 * cuando aparezca este error, se puede poner dentro de la descripción
 * <il>
 * </ul>
 *
 * @author christmo
 * @version 1.0, 05/Sep/2013
 */
public class XplookError implements Serializable {

    private Object errorCode;
    private String description;
    private ErrorType errorType;
//    private Map<String, Object> parameters = new HashMap<String, Object>();
    private Throwable exception;

    /**
     * Constructor para setear una excepción con un código de error
     *
     * @param errorCode código de error que se desea asignar
     * @param exception Excepción java lanzada por el sistema
     */
    public XplookError(Object errorCode, Throwable exception) {
        this.errorCode = errorCode;
        this.exception = exception;
        this.description = exception.getClass().getName() + ":" + exception.getMessage();
    }

    /**
     * Setea un mensaje de error con un determinado tipo, con el header del
     * mensaje para poder buscar el mensaje insternacionalizado y un codigo de
     * error para obterner este.
     *
     * @param type Tipo de mensaje de error
     * @param header Header del paquete
     * @param errorCode Código de error
     */
    public XplookError(ErrorType type, XplookHeader header, Object errorCode) {
        this.errorCode = errorCode;
        this.description = getErrorDescription(errorCode, header);
        this.errorType = type;
        this.exception = new Exception(description);
    }
//
//    public XplookError(ErrorType type, XplookHeader header, Throwable exception) {
//        this.errorCode = exception.getClass().getSimpleName();
//        this.description = getErrorDescription(errorCode, header);
//        this.exception = exception;
//        this.errorType = type;
//    }

    /**
     * Setea un mensaje de error en el paquete a partir de un mensaje de error
     *
     * @param type Tipo de mensaje de error
     * @param header del paquete para determinar el idioma
     * @param errorCode código de error
     * @param parameters parametros a ser presentados en el mensaje del paquete
     */
    public XplookError(ErrorType type, XplookHeader header, Object errorCode, Object... parameters) {
        this.errorCode = errorCode;
        this.description = getErrorDescription(errorCode, header, parameters);
        this.errorType = type;
        this.exception = null;
//        this.exception.initCause(new Throwable(description));
    }

    /**
     * Setea el mensaje de error en el paquete, a partir de una excepción java
     *
     * @param type Tipo de mensaje de error que será presentado
     * @param header del paquete para determinar el idioma del mensaje
     * @param exception código de excepción java lanzado por la aplicación
     * @param parameters parámetros a ser presentados en el mensaje
     */
    public XplookError(ErrorType type, XplookHeader header, Throwable exception, Object... parameters) {
        this.errorCode = exception.getClass().getSimpleName();
        this.description = getErrorDescription(errorCode, header, parameters);
        this.exception = exception;
        this.errorType = type;
    }

//    public XplookError(Object errorCode, String description) {
//        this.errorCode = errorCode;
//        this.description = description;
//    }
    /**
     * Obtiene el código de error
     *
     * @return Código de error
     */
    public Object getErrorCode() {
        return errorCode;
    }

    /**
     * Setea el error a partir de un código
     *
     * @param errorCode Código de error para ser cargado en el paquete
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        loadErrorByCode(errorCode);
    }

    /**
     * Setea el error con un determinado tipo de error dentro del mensaje
     *
     * @param errorCode Código de error
     * @param errorType Tipo de Error, Info, Warn
     */
    public void setErrorCode(int errorCode, ErrorType errorType) {
        this.errorCode = errorCode;
        loadErrorByCode(errorCode);
        this.errorType = errorType;
    }

    /**
     * Obtiene la descripción del mensaje de error
     *
     * @return La descripción del mensaje de error a ser presentado al usuario
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setea el mensaje de descriptivo del error, este puede contener secciones
     * que serán puestos los parametros para que se haga mas descriptivo el
     * mensaje para el usuario.
     * <p>
     * <code>Ejemplo de mensaje con -=1=- parámetro</code>
     * <p>
     * los parametros se irán seteando en el orden en que se ingresen
     *
     * @param description mensaje a visualizar por el usuario
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Obtiene el tipo de mensaje de error que contiene el paquete
     *
     * @return Tipo de mensaje ERROR, INFO, WARN
     */
    public ErrorType getErrorType() {
        return errorType;
    }

    /**
     * Setea el tipo de mensaje de error que contendrá el paquete
     *
     * @param errorType El tipo de mensaje puede ser ERROR, INFO, WARN
     */
    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

//    /**
//     * @return the parameters
//     */
//    public Map<String, Object> getParameters() {
//        return parameters;
//    }
//    /**
//     * @param parameters the parameters to set
//     */
//    public void setParameters(Map<String, Object> parameters) {
//        this.parameters = parameters;
//    }
//
//    public void setParameters(String key, Object value) {
//        this.parameters.put(key, value);
//    }
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    /**
     * Obtiene la excepción arrojada por java
     *
     * @return Obtiene la excepción que contiene el mensaje
     */
    public Throwable getException() {
        return exception;
    }

    /**
     * Setea un error a través de la excepción arrojada por java
     *
     * @param exception
     */
    public void setException(Throwable exception) {
        this.exception = exception;
    }

    /**
     * Obtiene el mensaje de error que se tiene que adjuntar al paquete para su
     * visualización, si tiene parametros estos seran reemplazados por el
     * comodín de tipo -=d=-, donde d representa cualquier número empesando en
     * 1, este tipo de comodines puede ser ubicado en cualquier lugar y orden
     * dentro del mensaje, lo parametros seran ubicados en el orden que se
     * ingresen.
     * <p>
     *
     * @param errorCode Código de error que puede ser en cualquier formato
     * dependiendo de cual sea la organización que se aplique para los errores
     * @param header Encabezado del paquete principalmente se lo utiliza para de
     * ahí obtener el lenguaje en el que se deben presentar los mensajes de
     * error
     * @param params este es un arreglo de datos, el cual se reemplazará en la
     * descripción del error para brindar mas información al usuario
     * @return Se obtiene en texto el mensaje de descripción del error, agregado
     * cada uno de los parámetros donde se haya asignado los comodines
     * @throws IllegalFormatConversionException
     */
    private String getErrorDescription(Object errorCode, XplookHeader header, Object[] params) throws IllegalFormatConversionException {
        String output = null;
        if (errorCode != null) {
            String desc = getErrorDescription(errorCode, header);
            output = desc;
            if (params != null || params.length != 0) {
                output = addParametersMessage(desc, params);
            }
        }
        return output;
    }

    /**
     * Reemplaza los parámetros que han sido agregados al mensaje de error, para
     * la visualización del mensaje en el paquete. Ejemplo:
     * <p>
     * <b>entrada:</b> [0] -> prueba<br/>
     * <code>Mensaje de -=1=-</code>
     * <p>
     * <b>salida:</b>
     * <code>Mensaje de prueba</code>
     *
     * @param message es el mensaje del erro, la entrada cada uno de los
     * parametros debe ir en el formato -=N=-
     * @param params arreglo de parametros dependera del numero que tenga en la
     * notacion -=N=-
     * @return Texto formateado con los parametros para que sea presentado al
     * usuario
     */
    private String addParametersMessage(String message, Object... params) {
        if (message != null) {
            CharSequence sec = CharMatcher.anyOf("-=").retainFrom(message);
            int paramsInMessage = sec.length() / 4;
            int paramsToReplase = params.length;
            int parameters = paramsInMessage >= paramsToReplase ? paramsToReplase : paramsInMessage;

            for (int i = 1; i <= parameters; i++) {
                message = message.replaceFirst("-=" + i + "=-", "" + params[i - 1]);
            }
        }
        return message;
    }

    /**
     * Obtiene el mensaje de error a ser presentado en el paquete, tomando en
     * cuenta el lenguaje del paquete, con la posibilidad de que sea
     * internacionalizado
     *
     * @param errorCode Código de error a buscar
     * @param header Header del paquete para obtener el lenguaje
     * @return Texto del mensaje de error para ser visualizado
     */
    private String getErrorDescription(Object errorCode, XplookHeader header) {
        String desc = " Implementar -=1=- este %s Met[]odo ;-) %s -=2=-";
        return desc;
    }

    /**
     * Cargar los errores por el tipo de código
     *
     * @param errorCode
     */
    private void loadErrorByCode(int errorCode) {
        IXplookDB db = new XplookMongoImpl();
//        db.findByKeyValue("error", errorCode);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
