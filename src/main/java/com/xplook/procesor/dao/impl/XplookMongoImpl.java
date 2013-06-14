/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.procesor.dao.impl;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.xplook.packager.XplookError;
import com.xplook.packager.XplookPacket;
import com.xplook.packager.XplookPacketData;
import com.xplook.procesor.dao.IXplookDB;
import java.net.UnknownHostException;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christmo
 */
public class XplookMongoImpl extends IXplookDB<XplookPacket> {

    private Logger log = LoggerFactory.getLogger(XplookMongoImpl.class);
    private DB db;

    public void getConection(XplookPacket pack) throws UnknownHostException {
        MongoClient mongo = new MongoClient();
        String database = pack.getPacketData(0).getDataBase();
        log.info("DataBase: " + database);
        db = mongo.getDB(database);
    }

    /**
     * Inserta el registro que venga en la solicitud generando automaticamente
     * un ID el cual es devuelto en el ID del paquete
     *
     * @param pack
     */
    public XplookPacket insert(XplookPacket pack) {
        for (XplookPacketData data : pack.getPacketData()) {
            DBCollection collection = db.getCollection(data.getCollection());
            log.info("Database:" + db.getName());
            log.info("Collection:" + data.getCollection());
            DBObject obj = new BasicDBObject();
            obj.putAll(data.getRequest());
            if (data.getId() != null && !data.getId().equals("")) {
                obj.put("_id", new ObjectId(data.getId()));
            }
            collection.save(obj);
            data.setId(obj.get("_id").toString());
        }
        return pack;
    }

    /**
     * Elimina un registro por el ID
     *
     * @param pack
     */
    public XplookPacket delete(XplookPacket pack) {
        for (XplookPacketData data : pack.getPacketData()) {
            DBCollection collection = db.getCollection(data.getCollection());
            log.info("Database:" + db.getName());
            log.info("Collection:" + data.getCollection());
//            obj.putAll(data.getRequest());
            if (data.getId() != null && !data.getId().equals("")) {
                DBObject obj = new BasicDBObject();
                obj.put("_id", new ObjectId(data.getId()));
                collection.findAndRemove(obj);
            } else {
                XplookError error = new XplookError(101, "No se ha enviado el id del registro a eliminar...");
                pack.addError(error);
            }
        }
        return pack;
    }

    /**
     * Busca la coincidencia del registro que tenga el mismo ID
     *
     * @param pack
     * @return XplookPacket
     */
    public XplookPacket findById(XplookPacket pack) {
        for (XplookPacketData data : pack.getPacketData()) {
            log.info("Collection: " + data.getCollection());
            DBCollection collection = db.getCollection(data.getCollection());

            DBObject fields = new BasicDBObject();
            fields.put("_id", false);

            DBObject obj = new BasicDBObject();
            obj.put("_id", new ObjectId(data.getId()));

            obj = collection.findOne(obj, fields);
            data.addResponse(obj.toMap());
        }
        return pack;
    }

    /**
     * Busca todas las coincidencias que vengan dentro de la solicitud clave y
     * valor igual dentro de una coleccion
     *
     * @param pack
     * @return XplookPacket
     */
    public XplookPacket findByKeyValue(XplookPacket pack) {
        for (XplookPacketData data : pack.getPacketData()) {
            log.info("Collection: " + data.getCollection());
            DBCollection collection = db.getCollection(data.getCollection());

            DBObject fields = new BasicDBObject();
            fields.put("_id", false);

            DBObject objQuery = new BasicDBObject();
            objQuery.putAll(data.getRequest());

            //For example, to get an array of the 1000-1100th elements of a cursor, use
            // List obj = collection.find( query ).skip( 1000 ).limit( 100 ).toArray();
            DBCursor cursor = collection.find(objQuery, fields);
            log.info("Cursor Size: " + cursor.size());
//            for (DBObject obj : cursor) {
            for (int i = 0; i < cursor.size(); i++) {
                DBObject obj = cursor.toArray().get(i);
                data.addResponse("" + i, obj.toMap());
            }
        }
        return pack;
    }

    /**
     * Elimina todos los registros que concuerden con una determinada clave y
     * valor que venga en la solicitud
     *
     * @param pack
     */
    public XplookPacket deleteByKeyValue(XplookPacket pack) {
        for (XplookPacketData data : pack.getPacketData()) {
            log.info("Collection: " + data.getCollection());
            DBCollection collection = db.getCollection(data.getCollection());

            DBObject objQuery = new BasicDBObject();
            if (data.getRequest() != null && !data.getRequest().isEmpty()) {
                objQuery.putAll(data.getRequest());
                collection.remove(objQuery);
            } else {
                XplookError error = new XplookError();
                error.setErrorCode(100);
                error.setDescription("No se ha enviado una solicitud para eliminar...");
                pack.addError(error);
            }
        }
        return pack;
    }
}
