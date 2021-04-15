package org.iskon.controllers;

import java.util.Date;
import java.util.UUID;

import org.iskon.models.Event;
import org.iskon.models.Notification;
import org.iskon.models.NotificationApproval;
import org.iskon.models.Team;
import org.iskon.services.EventService;
import org.iskon.services.NotificationService;
import org.iskon.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;


@Component
public class NotificationWrapper{
	//update ntf bug resolved.
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	NotificationService ntfService;
	//generate ntfs
	public boolean generateNotification(Event event,String broadCastType) {
		NotificationApproval ntf = new NotificationApproval(); // set broadCast type to multiple in case of an update and single in case of create
		ntf.setCreatedBy(event.getCreatedBy());
		ntf.setTargetType("event");	
		if(broadCastType.equalsIgnoreCase("multiple")) {
			ntf.setCreatedBy(event.getUpdatedBy());
			ntf.setMessage("Request to update an event \""+event.getEventTitle()+"\""); }
		else {
			ntf.setCreatedBy(event.getCreatedBy());
			ntf.setMessage("Request to create an event \""+event.getEventTitle()+"\""); } 
		ntf.setCreatedTime(new Date());
		ntf.setMappingTableData("event");
		ntf.setTargetId(event.getId());
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}
	
	public boolean generateNotification(Team team,String broadCastType) {
		NotificationApproval ntf = new NotificationApproval();// set broadCast type to multiple in case of an update and single in case of create
		
		if(broadCastType.equalsIgnoreCase("multiple")) {
			ntf.setCreatedBy(team.getUpdatedBy());
			ntf.setMessage("Request to update a team \""+team.getTeamTitle()+"\"");}
		else {
			ntf.setCreatedBy(team.getCreatedBy());
			ntf.setMessage("Request to create a team \""+team.getTeamTitle()+"\"");}
		ntf.setCreatedTime(team.getCreatedTime());
		ntf.setMappingTableData("team");
		ntf.setTargetType("team");
		ntf.setTargetId(team.getId());
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}
	
	
	public boolean generateNotification(User user) {
		NotificationApproval ntf = new NotificationApproval(); 
		ntf.setCreatedBy(user.getEmail());
		ntf.setCreatedTime(user.getCreatedTime());
		ntf.setMappingTableData("user");
		ntf.setTargetType("user");
		ntf.setMessage("New User created");
		ntf.setTargetId(user.getId());
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}
	
	public boolean cancelInvite(Event event) {
		Event event1 = eventService.getEventById(event.getId());
		Notification ntf = new Notification();
		ntf.setBroadcastType("single");
		ntf.setCreatedBy(event.getCreatedBy());
		ntf.setTargetType("event");	
		ntf.setMessage("The event \""+event.getEventTitle()+"\" has been cancelled");  
		ntf.setCreatedTime(new Date());
		ntf.setMappingTableData("event");
		ntf.setTargetId(event.getId());
		ntf.setUuid(UUID.randomUUID());
		event1.setStatus(3);
		return ntfService.saveNotificationCancel(ntf);
	}

}