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
	
	public boolean generateNotification(Event event,String broadCastType) {
		NotificationApproval ntf = new NotificationApproval();
		ntf.setCreatedBy(event.getCreatedBy());
		ntf.setTargetType("event");
		if(broadCastType.equalsIgnoreCase("multiple"))
			ntf.setMessage("Updated event "+event.getEventTitle()+"! Need approval");
		else
			ntf.setMessage("New event "+event.getEventTitle()+" created! Need approval");
		ntf.setCreatedTime(new Date());
		ntf.setMappingTableData("event");
		ntf.setTargetId(event.getId());
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}
	
	public boolean generateNotification(Team team,String broadCastType) {
		NotificationApproval ntf = new NotificationApproval();
		ntf.setCreatedBy(team.getCreatedBy());
		if(broadCastType.equalsIgnoreCase("multiple"))
			ntf.setMessage("Updated team "+team.getTeamTitle()+"! Need approval");
		else
			ntf.setMessage("New team "+team.getTeamTitle()+" created! Need approval");
		ntf.setMessage("New Team "+team.getTeamTitle()+" created! Need Approval");
		ntf.setCreatedTime(team.getCreatedTime());
		ntf.setMappingTableData("team");
		ntf.setTargetType("team");
		ntf.setTargetId(team.getId());
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}
	
	public boolean generateNotification(User user) {
		NotificationApproval ntf = new NotificationApproval();
		ntf.setCreatedBy("SYSTEM");
		ntf.setCreatedTime(user.getCreatedTime());
		ntf.setMappingTableData("user");
		ntf.setTargetType("user");
		ntf.setMessage("New User Created! Need Approval");
		ntf.setTargetId(user.getId());
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}

}