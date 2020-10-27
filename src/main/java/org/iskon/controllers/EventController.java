package org.iskon.controllers;

import org.iskon.models.Event;
import org.iskon.models.EventSearch;
import org.iskon.models.NotificationApproval;
import org.iskon.services.EventService;
import org.iskon.services.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/event")
public class EventController {

	@Autowired
	private EventService eventService;

	@Autowired
	private NotificationService ntfService;
	@GetMapping("/getdummyevent")
	public List<Event> getDummyEvent() { 		
		List<Event> eventreqs = new ArrayList<Event>();
		Event req = getDummyEventObj();	
		eventreqs.add(req);
		return eventreqs;
	}
	
	@PutMapping("/addevent")
	public Event addEvent(@RequestBody Event newEvent) {
		Event req = eventService.addEvent(newEvent);
		NotificationApproval ntf = new NotificationApproval();
		ntf.setBroadcastType("multiple");
		ntf.setCreatedBy(1);
		ntf.setCreatedTime(newEvent.getCreatedTime());
		ntf.setMappingTableData("event");
		ntf.setTargetType("event");
		ntf.setTargetId(newEvent.getId());
		ntf.setUuid(UUID.randomUUID());
		ntfService.saveNotificationAppr(ntf);
		return req;
	}
	
	@PutMapping("/updateevent")
	public Event updateEvent(@RequestBody Event newEvent) {
		System.out.println(newEvent);
		Event req = eventService.updateEvent(newEvent);
		NotificationApproval ntf = new NotificationApproval();
		ntf.setBroadcastType("multiple");
		ntf.setCreatedBy(1);
		ntf.setCreatedTime(newEvent.getCreatedTime());
		ntf.setMappingTableData("event");
		ntf.setTargetType("event");
		ntf.setTargetId(newEvent.getId());
		ntf.setUuid(UUID.randomUUID());
		ntfService.saveNotificationAppr(ntf);
		return req;
	}
	
	@PutMapping("/deleteevent")
	public void deleteEvent(@RequestBody Event newEvent) {
		System.out.println(newEvent);
		eventService.deleteEvent(newEvent);
		/*NotificationWrapper nw = new NotificationWrapper();
		nw.populateEventNotification(newEvent);*/
	}
	
	@PutMapping("/getevents")
	public List<Event> getEvents(@RequestBody EventSearch eventSearch) {
		List<Event> req = eventService.getEvents(eventSearch);
		return req;
	}
	
	@PutMapping("/processevent")
	public Boolean processevent(@RequestBody Event newEvent) {
		Boolean req = eventService.processEvent(newEvent);
		return req;
	}
	
	private Event getDummyEventObj()
	{
		Event er = Event.buildEvent(null, "Dummy Event Title", "Dummy Event Desc", new Date(), "1 hour",
				"Pune",
				"Sandhya", (long) 123456788, "Add Line One", "Add Line Two", "Add Line Three", "Camp", "Pune",
				411014, "Maharashtra", "India", false, "Draft", null, "Chinmay",
				"Manjunath", new Date(), new Date());
		return er;
	}

}
