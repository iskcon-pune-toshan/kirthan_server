package org.iskon.controllers;

import java.util.UUID;

import org.iskon.models.Event;
import org.iskon.models.NotificationApproval;
import org.iskon.models.Team;
import org.iskon.models.User;
import org.iskon.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationWrapper {
	
	@Autowired 
	NotificationService ntfService;
	
	public boolean generateNotification(Event event) {
		NotificationApproval ntf = new NotificationApproval();
		ntf.setBroadcastType("single");
		ntf.setCreatedBy(1);
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
		ntf.setCreatedTime(team.getCreatedTime());
		ntf.setMappingTableData("event");
		ntf.setTargetType("event");
		ntf.setTargetId(team.getId());
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}
	
	public boolean generateNotification(User user) {
		NotificationApproval ntf = new NotificationApproval();
		ntf.setBroadcastType("single");
		ntf.setCreatedBy(1);
		ntf.setCreatedTime(user.getCreatedTime());
		ntf.setMappingTableData("event");
		ntf.setTargetType("event");
		ntf.setTargetId(user.getId());
		ntf.setUuid(UUID.randomUUID());
		return ntfService.saveNotificationAppr(ntf);
	}

}
