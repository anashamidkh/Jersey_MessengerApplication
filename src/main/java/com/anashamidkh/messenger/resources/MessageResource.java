package com.anashamidkh.messenger.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
//import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.anashamidkh.messenger.modal.Message;
import com.anashamidkh.messenger.resources.beans.MessageFilterBean;
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
	
	/*@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@QueryParam("year") int year,				//This QueryParam is use for filtering
									 @QueryParam("start") int start,			//This QueryParam is use for pagination
									 @QueryParam("size") int size)*/				//This QueryParam is use for pagination
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessages(@BeanParam MessageFilterBean filterBean)	//BeanParam Implementation
	{
		if(filterBean.getYear() > 0) {
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart() > 0 && filterBean.getSize() > 0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Path("{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Message getMessage(@PathParam("messageId") long id) {
		return messageService.getMessage(id);
	}
	
	/*@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message addMessage(Message message) {	//Here in argument jersey 
													//converts json to object of "Message"
		return messageService.addMessage(message);
	}*/
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addMessage(Message message, @Context UriInfo uriInfo) {
		Message newMessage = messageService.addMessage(message);
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
		//return Response.status(Status.CREATED)
		//return Response.created(new URI("messenger/webapi/messages/" + newMessage.getId()))	//Its horrible. So we would uriInfo thing to get path automatically
		return Response.created(uri)
				.entity(newMessage)
				.build();
	}
	
	@PUT
	@Path("{messageId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId") long id,Message message) {
		message.setId(id);
		return messageService.updateMessage(message);
	}
		
	@DELETE
	@Path("{messageId}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId") long id) {
		messageService.removeMessage(id);
	}
	
	//Below code for implementation of subresources
	@Path("{messagesId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
	}
}