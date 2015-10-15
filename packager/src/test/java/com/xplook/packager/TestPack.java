/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.xplook.procesor.dao.IXplookDB;
import com.xplook.procesor.dao.XplookFactoryDAO;
import com.xplook.procesor.dao.impl.XplookMongoImpl;
import com.xplook.util.ActionType;
import com.xplook.util.ErrorType;
import com.xplook.util.Mode;
import com.xplook.util.ObjectType;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author christmo
 */
public class TestPack {

    @Before
    public void antes() {
        System.out.println("Antes...");
    }

    /**
     *
     */
    @Test
    public void TestPack() {
        XplookPacketData data = new XplookPacketData();
        data.setCollection("test");
        data.setDataBase("mongo");
        //data.setId("519dacc2cf43a2226066d1c2");
        //data.addRequest("valor", 12);
        data.addRequest("valor", "<p>126</p><br/>");
        //data.addResponse("addvalue", 23);
        //data.setMetadata("text", "christmo");

        XplookHeader header = new XplookHeader();
        header.setActionType(ActionType.DETAIL);
        header.setIdUser("1");
        header.setObjectType(ObjectType.ITEM);
        header.setMode(Mode.GET);

        //XplookError error = new XplookError();
        //error.setDescription("Mnesaje de error con parametros -=1=- ma descripcion!!! -=2=-");
        //error.setErrorCode(500);
        //error.setErrorType(ErrorType.ERROR);
        //error.setParameters("1", "parametro 1");
        //error.setParameters("2", "parametro 2");

        XplookPacket pack = new XplookPacket(data);
        //XplookPacket pack2 = new XplookPacket(null, data, null);
        pack.setHeader(header);
        pack.addError("TestError", "metodo","test","demo");
        //pack.addWarn("TestError", "metodo","test");
        //XplookPacket pack3 = new XplookPacket(header, data, error);

        //System.out.println(pack2);

        //XplookFactoryDAO dao = new XplookFactoryDAO();
        //IXplookDB<XplookPacket> mongo = dao.getDatabaseInstace();
        //IXplookDB<XplookPacket> mongo = new XplookMongoImpl();

        try {
            //mongo.getConection(pack);
            //mongo.insert(pack);
            //pack = mongo.findById(pack);
            //pack = mongo.findByKeyValue(pack);
            System.out.println(pack);
            //Assert.assertTrue(pack.toString(), true);
        } catch (Exception ex) {
            Logger.getLogger(TestPack.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
