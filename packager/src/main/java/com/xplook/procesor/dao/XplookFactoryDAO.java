/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.procesor.dao;

import java.net.URL;
import java.util.logging.Level;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christmo
 */
public class XplookFactoryDAO {

    private Logger log = LoggerFactory.getLogger(XplookFactoryDAO.class);
    private IXplookDB db;

    public XplookFactoryDAO() {
        try {
            URL url = getClass().getResource("/configuration.xml");
            log.info("" + url.getPath());
            XMLConfiguration config = new XMLConfiguration(url);
            log.info(config.getBasePath());

            Object prop = config.getProperty("database.implementation");
            log.info("Implementation:" + prop);
            try {
                if (prop != null) {
                    Class classImplementationDB = Class.forName(prop.toString());
                    db = (IXplookDB) classImplementationDB.newInstance();
                }else{
                    throw new IllegalArgumentException("No implementation file exception...");
                }
            } catch (ClassNotFoundException ex) {
                log.error("La clase de la implementacion no ha sido encontrada...",ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(XplookFactoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(XplookFactoryDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ConfigurationException cex) {
            // something went wrong, e.g. the file was not found
        }
    }

    /**
     * Obtiene Instancia de la conexion a la base de datos
     * @return the db
     */
    public IXplookDB getDatabaseInstace() {
        return db;
    }
}
