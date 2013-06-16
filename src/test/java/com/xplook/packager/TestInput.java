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
     /*   String data = "{\"header\":"
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
                + "\"response\":{\"\":{\"text\":\"christmo\",\"valor\":100},\"test2\":{\"algo\":[1,2]}}}],"
                + "\"error\":"
                + "[{"
                + "\"errorCode\":500,"
                + "\"description\":\"se me hizo verga la bici... listo\","
                + "\"errorType\":\"ERROR\","
                + "\"parameters\":{\"2\":\"listo\",\"1\":\"verga\"}}]}";

        Synapses syn = new Synapses();
        XplookPacket xp = syn.packagerConverter(data);

        System.out.println(xp.getPacketData(0).getDataBase());
        System.out.println(xp.getPacketData(0).getCollection());
        System.out.println(xp.getPacketData(0).getId());
        System.out.println(xp.getHeader().getMode());
        for (XplookColumn column : xp.getPacketData(0).getRow("").getColumns()) {
            System.out.println(column.getKey() + " = " + column.getValue());
        }


        System.out.println(((ArrayList<Integer>)xp.getPacketData(0).getCell("test2", "algo")).get(0));*/
    }
}
