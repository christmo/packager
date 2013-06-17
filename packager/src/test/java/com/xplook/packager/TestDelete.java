/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.xplook.procesor.dao.IXplookDB;
import com.xplook.procesor.dao.XplookFactoryDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author christmo
 */
public class TestDelete {

    @Test
    public void deleteRow() {
        XplookPacketData data = new XplookPacketData();
        data.setCollection("test");
        data.setDataBase("mongo");
//        data.setId("519db288cf43f916cbcb434f");
        data.getRequest().put("text", "christmo");

        XplookPacket pack = new XplookPacket(data);

        System.out.println(pack);

        XplookFactoryDAO dao = new XplookFactoryDAO();
        IXplookDB<XplookPacket> mongo = dao.getDatabaseInstace();

        try {
            mongo.getConection(pack);
            pack = mongo.delete(pack);
//            pack = mongo.deleteByKeyValue(pack);
            System.out.println(pack);
        } catch (Exception ex) {
            Assert.assertFalse(pack.toString(), true);
            Logger.getLogger(TestPack.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertTrue(pack.toString(), true);
    }
}
