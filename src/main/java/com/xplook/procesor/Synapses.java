/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.procesor;

import com.google.gson.Gson;
import com.xplook.packager.XplookPacket;
import com.xplook.procesor.dao.IXplookDB;
import com.xplook.procesor.dao.XplookFactoryDAO;

/**
 *
 * @author christmo
 */
public class Synapses {

    public Synapses() {
    }

    public XplookPacket convertJSONToPacket(String packJson) {
        Gson gson = new Gson();
        XplookPacket pack = gson.fromJson(packJson, XplookPacket.class);
        return pack;
    }

    public XplookPacket processPacket(XplookPacket pack) {
        XplookFactoryDAO dao = new XplookFactoryDAO();
        IXplookDB<XplookPacket> dbInstance = dao.getDatabaseInstace();
        switch (pack.getHeader().getMode()) {
            case POST:
                pack = dbInstance.insert(pack);
            case DELETE:
                dbInstance.delete(pack);
            case UPDATE:
                dbInstance.update(pack);
            case GET:
                pack = dbInstance.findById(pack);
        }
        return pack;
    }
}
