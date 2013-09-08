/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.procesor;

import com.google.gson.Gson;
import com.xplook.packager.XplookPacket;
import com.xplook.procesor.dao.IXplookDB;
import com.xplook.procesor.dao.XplookFactoryDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.configuration.ConfigurationException;

/**
 * Esta clase se encarga de hacer la conexión entre la interfaz y la base de
 * datos intercambiando paquetes de información
 *
 * @author christmo
 * @version 1.0, 08/Sep/2013
 *
 */
public class Synapses {

    /**
     * Contructor por defecto
     */
    public Synapses() {
    }

    /**
     * Convierte el texto ingresado en formato JSON a un Paquete, este texto
     * debe corresponder a la especificación del paquete
     *
     * @param packJson
     * @return XplookPacket
     */
    public XplookPacket convertJSONToPacket(String packJson) {
        Gson gson = new Gson();
        XplookPacket pack = gson.fromJson(packJson, XplookPacket.class);
        return pack;
    }

    /**
     * Procesa el paquete dependiendo del modo en que llega, para tomar las
     * distintas acciones con los datos que trae el paquete.
     *
     * @param pack Paquete con datos y un determinado modo: GET, POST, DELETE,
     * UPDATE, los cuales se veran reflejados en acciones sobre la base de datos
     * @return XplookPacket dependiendo del modo el paquete traerá determinada
     * información en caso de ser de consulta o de inserción/actualización
     */
    public XplookPacket processPacket(XplookPacket pack) {
        try {
            XplookFactoryDAO dao = new XplookFactoryDAO();
            IXplookDB<XplookPacket> dbInstance = dao.getDatabaseInstace(pack);

            switch (pack.getHeader().getMode()) {
                case POST:
                    pack = dbInstance.insert(pack);
                    break;
                case DELETE:
                    dbInstance.delete(pack);
                    break;
                case UPDATE:
                    dbInstance.update(pack);
                    break;
                case GET:
                    pack = dbInstance.findById(pack);
                    break;
            }
        } catch (ConfigurationException ex) {
            pack.addError(1, ex);
            Logger.getLogger(Synapses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            pack.addError(2, ex);
            Logger.getLogger(Synapses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            pack.addError(3, ex);
            Logger.getLogger(Synapses.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            pack.addError(4, ex);
            Logger.getLogger(Synapses.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pack;
    }
}
