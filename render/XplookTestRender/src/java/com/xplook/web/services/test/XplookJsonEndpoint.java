/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xplook.web.services.test;

import com.xplook.packager.XplookError;
import com.xplook.packager.XplookHeader;
import com.xplook.packager.XplookPacket;
import com.xplook.packager.XplookPacketData;
import com.xplook.util.ActionType;
import com.xplook.util.ErrorType;
import com.xplook.util.Mode;
import com.xplook.util.ObjectType;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Santiago
 */
@Path("/JSONEndPoint")
public class XplookJsonEndpoint implements Serializable{
  
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response processPacket(@Context HttpServletRequest req) throws URISyntaxException {

    XplookPacketData data = new XplookPacketData();
    data.setCollection("test");
    data.setDb("mongo");
    data.setNumberChild(1);
    data.setId("D-1");
    data.setValue("Texto del Campo");
    data.setMetadata("Valor", 100);
    data.setMetadata("text", "christmo");

    XplookHeader header = new XplookHeader();
    header.setActionType(ActionType.DETAIL);
    header.setIdUser("1");
    header.setObjectType(ObjectType.ITEM);
    header.setMode(Mode.GET);

    XplookError error = new XplookError();
    error.setDescription("se me hizo -=1=- la bici... -=2=-");
    error.setErrorCode(500);
    error.setErrorType(ErrorType.ERROR);
    error.setParameters("1", "verga");
    error.setParameters("2", "listo");

    XplookPacket pack = new XplookPacket(header, data, error);
    
    String[] pars = req.getParameterValues("xplook_packet");
    for (String string : pars) {
      System.out.println(string);
    }

    return Response.ok().entity(pack.toString()).build();
  }

}
