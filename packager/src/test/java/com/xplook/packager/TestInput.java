/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.packager;

import com.xplook.procesor.Synapses;
import java.util.ArrayList;
import org.junit.Test;

/**
 *
 * @author christmo
 */
public class TestInput {

    @Test
    public void TestInput() {
        String data = "{\"header\":"
                + "{"
                + "\"idUser\":\"1\","
                + "\"mode\":\"GET\","
                + "\"actionType\":\"DETAIL\","
                + "\"objectType\":\"ITEM\""
                + "},"
                + "\"packetData\":"
                + "[{"
                + "\"numberChild\":1,"
                + "\"value\":\"Texto del Campo\","
                + "\"id\":\"D-1\","
                + "\"db\":\"mongo\","
                + "\"collection\":\"test\","
                + "\"response\": [\n"
                + "        {\n"
                + "          \"rowId\": \"0\",\n"
                + "          \"columns\": [\n"
                + "            {\n"
                + "              \"key\": \"0\",\n"
                + "              \"value\": {\n"
                + "                \"valor\": 126\n"
                + "              }\n"
                + "            },\n"
                + "            {\n"
                + "              \"key\": \"1\",\n"
                + "              \"value\": {\n"
                + "                \"valor\": 126\n"
                + "              }\n"
                + "            }\n"
                + "          ]\n"
                + "        }\n"
                + "      ]}],"
                + "\"error\":"
                + "[{"
                + "\"errorCode\":500,"
                + "\"description\":\"Esto representa un mensaje de error -=1=-\","
                + "\"errorType\":\"ERROR\","
                + "\"parameters\":{\"2\":\"parametro2\",\"1\":\"parametro1\"}}]}";

        Synapses syn = new Synapses();
        XplookPacket xp = syn.convertJSONToPacket(data);

        System.out.println(xp.getPacketData(0).getDataBase());
        System.out.println(xp.getPacketData(0).getCollection());
        System.out.println(xp.getPacketData(0).getId());
        System.out.println(xp.getHeader().getMode());
        for (XplookColumn column : xp.getPacketData(0).getRow("0").getColumns()) {
            System.out.println(column.getKey() + " = " + column.getValue());
        }


//        System.out.println(((ArrayList<Integer>) xp.getPacketData(0).getCell("test2", "algo")).get(0));
    }
}
