package org.iskon.controllers;

import java.util.Date;
import java.util.UUID;

import org.iskon.models.Event;
import org.iskon.models.EventTeamSearch;
import org.iskon.models.Notification;
import org.iskon.models.NotificationApproval;
import org.iskon.models.Team;
import org.iskon.services.EventService;
import org.iskon.services.EventTeamService;
import org.iskon.services.NotificationService;
import org.iskon.services.TeamService;
import org.iskon.services.UserService;
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
	EventTeamService eventTeamService;
	
	@Autowired
	TeamService teamService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	NotificationService ntfService;
	//generate ntfs
	public boolean generateNotification(Event event,String broadCastType) {
//		NotificationApproval ntf = new NotificationApproval(); // set broadCast type to multiple in case of an update and single in case of create
//		ntf.setCreatedBy(event.getCreatedBy());
//		ntf.setTargetType("event");	
		if(broadCastType.equalsIgnoreCase("multiple")) {
				Notification ntf = new Notification(); // set broadCast type to multiple in case of an update and single in case of create
				ntf.setCreatedBy(event.getCreatedBy());
				ntf.setBroadcastType("edit");
				ntf.setTargetType("event");	
				ntf.setCreatedBy(event.getUpdatedBy());
				ntf.setMessage("Request to update an event \""+event.getEventTitle()+"\"");
				System.out.println(event.getId());
				String teamLead = teamService.getTeamById(eventTeamService.getTeamId(event.getId())).getTeamLeadId();
				ntf.setTargetId(userService.getUserByEmailId(teamLead).get().getId());
				ntf.setCreatedTime(new Date());
				ntf.setUpdatedTime(new Date());
				ntf.setMappingTableData("event");
				ntf.setUuid(UUID.randomUUID());
				return ntfService.saveNotification(ntf);
			} //for public event
		else if(broadCastType.equalsIgnoreCase("public event")) {
				Notification ntf = new Notification(); 
				ntf.setCreatedBy(event.getCreatedBy());
				ntf.setBroadcastType("edit");
				ntf.setTargetType("event");	
				ntf.setCreatedBy(event.getUpdatedBy());
				ntf.setMessage("Event \""+event.getEventTitle()+"\" has been created");
				System.out.println(event.getId());
				ntf.setTargetId(userService.getUserByEmailId(event.getCreatedBy()).get().getId());
				ntf.setCreatedTime(new Date());
				ntf.setUpdatedTime(new Date());
				ntf.setMappingTableData("event");
				ntf.setUuid(UUID.randomUUID());
				return ntfService.saveNotification(ntf);
			}
		else {
				NotificationApproval ntf = new NotificationApproval(); // set broadCast type to multiple in case of an update and single in case of create
				ntf.setCreatedBy(event.getCreatedBy());
				ntf.setTargetType("event");	
				event.setStatus(1);
				ntf.setCreatedBy(event.getCreatedBy());
				ntf.setUpdatedTime(new Date());
				ntf.setMessage("Request to create an event \""+event.getEventTitle()+"\"");
				ntf.setTargetId(event.getId());
				
				ntf.setCreatedTime(new Date());
				ntf.setMappingTableData("event");
				
				ntf.setUuid(UUID.randomUUID());
				return ntfService.saveNotificationAppr(ntf);
		}
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
		ntf.setUpdatedTime(new Date());
		ntf.setMappingTableData("team");
		ntf.setTargetType("team");
		ntf.setTargetId(team.getId());
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}
	
	
	public boolean generateNotification(User user, String broadCastType) {
		if(broadCastType.equalsIgnoreCase("make local admin")) {
			Notification ntf = new Notification(); 
			ntf.setCreatedBy(user.getEmail());
			ntf.setCreatedTime(user.getCreatedTime());
			ntf.setBroadcastType("edit");
			ntf.setUpdatedTime(new Date());
			ntf.setUpdatedBy(userService.getUserById(user.getInvitedBy()).getEmail());
			ntf.setMappingTableData("user");
			ntf.setTargetType("user");
			ntf.setMessage("You have been promoted to local admin by " + ntf.getUpdatedBy());
			ntf.setTargetId(user.getId());
			user.setApprovalStatus("Approved");
			user.setApprovalComments("Approved");
			ntf.setUuid(UUID.randomUUID());
			return ntfService.saveNotification(ntf); 
		} else if(broadCastType.equalsIgnoreCase("make user")) {
			Notification ntf = new Notification(); 
			ntf.setCreatedBy(user.getEmail());
			ntf.setBroadcastType("edit");
			ntf.setCreatedTime(user.getCreatedTime());
			ntf.setUpdatedTime(new Date());
			//User user = userServ
			ntf.setUpdatedBy(userService.getUserById(user.getInvitedBy()).getEmail());
			ntf.setMappingTableData("user");
			ntf.setTargetType("user");
			ntf.setMessage("You have been made user by " + ntf.getUpdatedBy());
			ntf.setTargetId(user.getId());
			user.setApprovalStatus("Approved");
			user.setApprovalComments("Approved");
			ntf.setUuid(UUID.randomUUID());
			return ntfService.saveNotification(ntf); 
		}else if(broadCastType.equalsIgnoreCase("initiate team")) {
			Notification ntf = new Notification(); 
			ntf.setCreatedBy(user.getEmail());
			ntf.setBroadcastType("edit");
			ntf.setCreatedTime(user.getCreatedTime());
			ntf.setUpdatedTime(new Date());
			ntf.setUpdatedBy(userService.getUserById(user.getInvitedBy()).getEmail());
			ntf.setMappingTableData("user");
			ntf.setTargetType("user");
			ntf.setMessage("You have been invited to create a team by " + ntf.getUpdatedBy());
			ntf.setTargetId(user.getId());
			user.setApprovalStatus("Approved");
			user.setApprovalComments("Approved");
			ntf.setUuid(UUID.randomUUID());
			return ntfService.saveNotification(ntf); 
		}else {
			NotificationApproval ntf = new NotificationApproval(); 
			ntf.setCreatedBy(user.getEmail());
			ntf.setCreatedTime(user.getCreatedTime());
			ntf.setUpdatedTime(new Date());
			ntf.setMappingTableData("user");
			ntf.setTargetType("user");
			ntf.setMessage("New User created");
			ntf.setTargetId(user.getId());
			user.setApprovalStatus("Approved");
			user.setApprovalComments("Approved");
			ntf.setUuid(UUID.randomUUID());
			return ntfService.saveNotificationAppr(ntf); 
		}
	}
	
	
	public boolean cancelInvite(Event event) {
		Event event1 = eventService.getEventById(event.getId());
		Notification ntf = new Notification();
		ntf.setBroadcastType("single");
		ntf.setCreatedBy(event.getCreatedBy());
		ntf.setTargetType("event");	
		ntf.setMessage("The event \""+event.getEventTitle()+"\" has been cancelled due to " + event.getCancelReason());  
		ntf.setCreatedTime(new Date());
		ntf.setUpdatedTime(new Date());
		ntf.setMappingTableData("event");
		ntf.setTargetId(event.getId());
		ntf.setUuid(UUID.randomUUID());
		event1.setStatus(3);
		return ntfService.saveNotificationCancel(ntf);
	}
	
	public boolean generateNotification(Event event) {
		Notification ntf = new Notification();
		ntf.setCreatedBy(event.getCreatedBy());
		ntf.setBroadcastType("multiple");
		ntf.setCreatedTime(new Date());
		ntf.setUpdatedTime(new Date());
		ntf.setMappingTableData("event");
		ntf.setTargetType("event");
		ntf.setMessage("Registered Succcesfully for " + event.getEventTitle() + " ");
		ntf.setTargetId(event.getId());
		ntf.setUuid(UUID.randomUUID());
		
		return ntfService.saveNotification(ntf);
	}
	
}