package com.anashamidkh.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)

public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String getParramsUsingAnnotations(@MatrixParam("param") String matrixParam,
											 @HeaderParam("customHeaderValue") String header,
											 @CookieParam("name") String cookie) {
		return "MatrixParam: " +matrixParam + " HeaderParam: " +header + " CookieParam: " +cookie;
	}
}