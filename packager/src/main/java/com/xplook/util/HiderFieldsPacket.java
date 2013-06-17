/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author christmo
 */
public class HiderFieldsPacket<T> implements ExclusionStrategy {

    private Logger log = LoggerFactory.getLogger(HiderFieldsPacket.class);

    private void iteratePacketMapEntry(Set<Map.Entry<String, JsonElement>> setMapEntry) {
        for (Map.Entry<String, JsonElement> pack : setMapEntry) {
            iteratePacket(pack);
        }
    }

    private void iteratePacket(Map.Entry<String, JsonElement> pack) {
        //log.info("[field]" + pack.getKey() + "=" + pack.getValue()+" / "+pack.getValue().toString().equals("{}"));
        if (pack.getValue().isJsonObject()) {
            iteratePacketMapEntry(pack.getValue().getAsJsonObject().entrySet());
        } else if (pack.getValue().isJsonArray()) {
            for (int i = 0; i < pack.getValue().getAsJsonArray().size(); i++) {
                JsonElement fields = pack.getValue().getAsJsonArray().get(i);
                if (fields.isJsonObject()) {
                    iteratePacketMapEntry(fields.getAsJsonObject().entrySet());
                }
            }
        }
    }

    public HiderFieldsPacket(T packet) {
        Gson json = new Gson();
        JsonElement jsonElement = json.toJsonTree(packet);

        iteratePacketMapEntry(jsonElement.getAsJsonObject().entrySet());

//        for (Map.Entry<String, JsonElement> pack : jsonElement.getAsJsonObject().entrySet()) {
//            log.info("[sections]" + pack.getKey() + "=" + pack.getValue().toString() + " --> " + pack.getValue().isJsonArray());
//
//            if (pack.getValue().isJsonObject()) {
//                for (Map.Entry<String, JsonElement> sections : pack.getValue().getAsJsonObject().entrySet()) {
//                    log.info("[fields]" + sections.getKey() + "=" + sections.getValue().toString());
//                }
//            } else if (pack.getValue().isJsonArray()) {
//                for (int i = 0; i < pack.getValue().getAsJsonArray().size(); i++) {
//                    JsonElement fields = pack.getValue().getAsJsonArray().get(i);
//                    log.info("[fields]" + fields);
//                    if (fields.isJsonObject()) {
//                        for (Map.Entry<String, JsonElement> sections : fields.getAsJsonObject().entrySet()) {
//                            log.info("[fields]" + sections.getKey() + "=" + sections.getValue().toString());
//                        }
//                    } else if (fields.isJsonArray()) {
//                        for (int j = 0; j < fields.getAsJsonArray().size(); j++) {
//                            JsonElement c = fields.getAsJsonArray().get(j);
//                            log.info("[fields]" + c);
//                        }
//                    }
//                }
//            }
//        }

//            JsonObject errorSection = errorIndex.getAsJsonObject();
//            JsonObject param = errorSection.getAsJsonObject("parameters");
//            for (Map.Entry<String, JsonElement> entry : param.entrySet()) {
//                String desc = errorSection.get("description").getAsString();
//                errorSection.addProperty("description", desc.replace("-=" + entry.getKey() + "=-", entry.getValue().getAsString()));
//            }

    }

    public boolean shouldSkipField(FieldAttributes f) {
        return false;
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
