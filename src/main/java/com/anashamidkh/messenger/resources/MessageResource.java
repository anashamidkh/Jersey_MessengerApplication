package com.anashamidkh.messenger.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.anashamidkh.messenger.modal.Message;
import com.anashamidkh.messenger.service.MessageService;

@Path("messages")
public class MessageResource {

MessageService messageService = new MessageService();
	
	//@GET
	//@Produces(MediaType.TEXT_PLAIN)
	//public String getMessages() {
		//return "Hello World";
	//}
	
	/*@GET
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages(){
		return messageService.getAllMessages();
	}*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(){
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long messageId) {
		return messageService.getMessage(messageId);
	}
}
