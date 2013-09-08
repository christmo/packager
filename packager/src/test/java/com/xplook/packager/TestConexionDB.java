/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.xplook.procesor.dao.IXplookDB;
import com.xplook.procesor.dao.XplookFactoryDAO;
import com.xplook.util.ActionType;
import com.xplook.util.Mode;
import com.xplook.util.ObjectType;
import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;

/**
 *
 * @author christmo
 */
public class TestConexionDB {

    @Test
    public void Test() {

        XplookPacketData data = new XplookPacketData();
        data.setCollection("test");
        data.setDataBase("mongo");
        data.addRequest("valor", 126);

        XplookHeader header = new XplookHeader();
        header.setActionType(ActionType.DETAIL);
        header.setIdUser("1");
        header.setObjectType(ObjectType.ITEM);
        header.setMode(Mode.GET);

//        XplookError error = new XplookError();
//        error.setErrorCode(500);
//        error.setErrorType(ErrorType.ERROR);

        XplookPacket pack = new XplookPacket(data);

        try {
            XplookFactoryDAO dao = new XplookFactoryDAO();
            IXplookDB db = dao.getDatabaseInstace(pack);

            System.out.println("" + db);
        } catch (ConfigurationException ex) {
            pack.addError(ex);
            //Logger.getLogger(TestConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            
//            pack.addError("TestError","demo","test");
            pack.addError(ex);
            //Logger.getLogger(TestConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            pack.addError(ex);
            //Logger.getLogger(TestConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            pack.addError(ex);
            //Logger.getLogger(TestConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(pack);
//        System.out.println(String.format("The %s %s was %s his %s /// %s", "brown", "dog", "eating", 1.2, 3L));
//        for(XplookError e:pack.getError()){
//            System.out.println(""+e.getErrorCode());
//            System.out.println(""+e.getDescription());
////            System.out.println(""+e.getException().getCause().getLocalizedMessage());
//            System.out.println(""+e.getException().getClass().getCanonicalName());
//            System.out.println(""+e.getException().getClass().getName());
//            System.out.println(""+e.getException().getClass().getSimpleName());
//            
//            System.out.println(""+e.getException().getStackTrace()[1].getFileName());
////            System.out.println(""+e.getException().getCause().toString());
//            e.getException().printStackTrace();
//        }
    }
}
