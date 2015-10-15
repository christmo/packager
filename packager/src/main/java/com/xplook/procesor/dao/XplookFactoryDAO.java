/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.procesor.dao;

import com.xplook.packager.XplookPacket;
import com.xplook.util.XplookConstants;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.HierarchicalConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christmo
 */
public class XplookFactoryDAO {

    private static Logger log = LoggerFactory.getLogger(XplookFactoryDAO.class);
    private static Map<String, DataBaseProperties> dbProperties = new ConcurrentHashMap<String, DataBaseProperties>();
    private static String defaultDataBaseTools;

    /**
     * Constructor de conexión a la base de datos
     * @throws ConfigurationException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public XplookFactoryDAO() throws ConfigurationException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        URL url = getClass().getResource(XplookConstants.PATH_PROPERTIES_DB);
        log.info("" + url.getPath());
        XMLConfiguration config = new XMLConfiguration(url);
        log.info(config.getBasePath());

        defaultDataBaseTools = config.getString(XplookConstants.TAG_DEFAULT_DB);
        List<HierarchicalConfiguration> servers = config.configurationsAt(XplookConstants.TAG_DATABASES);

        for (HierarchicalConfiguration server : servers) {
            String service = server.getString(XplookConstants.TAG_NAME_SERVICE);
            DataBaseProperties dbProp = new DataBaseProperties(service);
            dbProp.addProperty(XplookConstants.TAG_CLASS_IMPL, server.getString(XplookConstants.TAG_CLASS_IMPL));
            dbProp.addProperty(XplookConstants.TAG_USER_DB, server.getString(XplookConstants.TAG_USER_DB));
            dbProp.addProperty(XplookConstants.TAG_PASS_DB, server.getString(XplookConstants.TAG_PASS_DB));
            dbProp.addProperty(XplookConstants.TAG_SERVER_DB, server.getString(XplookConstants.TAG_SERVER_DB));
            dbProp.addProperty(XplookConstants.TAG_PORT_DB, server.getString(XplookConstants.TAG_PORT_DB));

            Class classImplementationDB = Class.forName((String) dbProp.getProperty(XplookConstants.TAG_CLASS_IMPL));
            IXplookDB db = (IXplookDB) classImplementationDB.newInstance();
            log.info("Add Service Engine DataBase: [" + service + "] -> " + classImplementationDB.getName());
            dbProp.addProperty(XplookConstants.TAG_CONNECTION_DB, db);

            dbProperties.put(service, dbProp);
        }
    }

    /**
     * Obtiene Instancia de la conexion a la base de datos
     * @param packet Paquete de información Header determina la base
     * @return IXplookDB Interfaz de Conexión a una base de datos
     */
    public IXplookDB getDatabaseInstace(XplookPacket packet) {
        DataBaseProperties prop;
        if (packet != null && packet.getHeader()!= null && packet.getHeader().getEngineDB() != null) {
            log.info("Get Instance Header: " + packet.getHeader().getEngineDB());
            prop = dbProperties.get(packet.getHeader().getEngineDB());
        } else {
            log.info("Get Instance Default: " + defaultDataBaseTools);
            prop = dbProperties.get(defaultDataBaseTools);
        }
        return (IXplookDB) prop.getProperty(XplookConstants.TAG_CONNECTION_DB);
    }
}
