package org.iskon.controllers;

import java.util.Date;
import java.util.UUID;

import org.iskon.models.Event;
import org.iskon.models.NotificationApproval;
import org.iskon.models.Team;
import org.iskon.services.NotificationService;
import org.iskon.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationWrapper{
	
	
	@Autowired
	NotificationService ntfService;
	//generate ntfs
	public boolean generateNotification(Event event,String broadCastType) {
		NotificationApproval ntf = new NotificationApproval(); // set broadCast type to multiple in case of an update and single in case of create
		ntf.setCreatedBy(event.getCreatedBy());
		ntf.setTargetType("event");	
		if(broadCastType.equalsIgnoreCase("multiple"))
			ntf.setMessage("Request to update an event \""+event.getEventTitle()+"\"");
		else
			ntf.setMessage("Request to create an event \""+event.getEventTitle()+"\"");
		ntf.setCreatedTime(new Date());
		ntf.setMappingTableData("event");
		ntf.setTargetId(event.getId());
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}
	
	public boolean generateNotification(Team team,String broadCastType) {
		NotificationApproval ntf = new NotificationApproval();// set broadCast type to multiple in case of an update and single in case of create
		ntf.setCreatedBy(team.getCreatedBy());
		if(broadCastType.equalsIgnoreCase("multiple"))
			ntf.setMessage("Request to update a team \""+team.getTeamTitle()+"\"");
		else
			ntf.setMessage("Request to create a team \""+team.getTeamTitle()+"\"");
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

}