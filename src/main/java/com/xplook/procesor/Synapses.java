/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.procesor;

import com.google.gson.Gson;
import com.xplook.packager.XplookPacket;

/**
 *
 * @author christmo
 */
public class Synapses {

    public Synapses() {
    }

    public XplookPacket packagerConverter(String packJson) {
        Gson gson = new Gson();
        XplookPacket pack = gson.fromJson(packJson, XplookPacket.class);
        return pack;
    }

    boolean savePackage(XplookPacket pack) {
        return false;
    }
}
