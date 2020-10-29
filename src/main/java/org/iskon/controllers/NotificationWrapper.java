package org.iskon.controllers;

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
	
	
	public boolean generateNotification(Event event) {
		NotificationApproval ntf = new NotificationApproval();
		ntf.setBroadcastType("single");
		ntf.setCreatedBy(1);
		ntf.setMessage("New Event Created!Need Approval");
		ntf.setCreatedTime(event.getCreatedTime());
		ntf.setMappingTableData("event");
		ntf.setTargetType("event");
		ntf.setTargetId(event.getId());
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}
	
	public boolean generateNotification(Team team) {
		NotificationApproval ntf = new NotificationApproval();
		ntf.setBroadcastType("single");
		ntf.setCreatedBy(1);
		ntf.setMessage("New Team Created! Needed Approval");
		ntf.setCreatedTime(team.getCreatedTime());
		ntf.setMappingTableData("team");
		ntf.setTargetType("team");
		ntf.setTargetId(team.getId());
		ntf.setMessage("New Event Created!Need Approval");
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}
	
	public boolean generateNotification(User user) {
		NotificationApproval ntf = new NotificationApproval();
		ntf.setBroadcastType("single");
		ntf.setCreatedBy(1);
		ntf.setCreatedTime(user.getCreatedTime());
		ntf.setMappingTableData("user");
		ntf.setTargetType("user");
		ntf.setMessage("New User Created! Need Approval");
		ntf.setTargetId(user.getId());
		ntf.setMessage("New Event Created!Need Approval");

		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}

}
