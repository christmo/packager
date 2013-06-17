/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.xplook.procesor.dao.IXplookDB;
import com.xplook.procesor.dao.impl.XplookMongoImpl;
import com.xplook.util.ErrorType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author christmo
 */
public class XplookError implements Serializable {

    private Object errorCode;
    private String description;
    private ErrorType errorType;
    private Map<String, Object> parameters = new HashMap<String, Object>();

    public XplookError(){
        
    }
    
    public XplookError(Object errorCode, String description){
        this.errorCode = errorCode;
        this.description = description;
    }
    
    /**
     * @return the errorCode
     */
    public Object getErrorCode() {
        return errorCode;
    }

    /**
     * @param errorCode the errorCode to set
     */
    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
        loadErrorByCode(errorCode);
    }

    public void setErrorCode(int errorCode, ErrorType errorType) {
        this.errorCode = errorCode;
        loadErrorByCode(errorCode);
        this.errorType = errorType;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the errorType
     */
    public ErrorType getErrorType() {
        return errorType;
    }

    /**
     * @param errorType the errorType to set
     */
    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    /**
     * @return the parameters
     */
    public Map<String, Object> getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public void setParameters(String key, Object value) {
        this.parameters.put(key, value);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    private void loadErrorByCode(int errorCode) {
        IXplookDB db = new XplookMongoImpl();
//        db.findByKeyValue("error", errorCode);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
