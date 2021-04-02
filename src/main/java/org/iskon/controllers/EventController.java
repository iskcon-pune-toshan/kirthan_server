package org.iskon.controllers;

import org.iskon.models.Event;
import org.iskon.models.EventSearch;
import org.iskon.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.threeten.bp.LocalDate;

import com.google.api.client.util.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/event")
public class EventController {

	@Autowired
	private EventService eventService;
	
	@Autowired
	private NotificationWrapper ntfWrapper;
	
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
		//NotificationWrapper ntfWrapper = new NotificationWrapper();
		ntfWrapper.generateNotification(req,"single");
		return req;
	}
	
	@PutMapping("/updateevent")
	public Event updateEvent(@RequestBody Event newEvent) {
		System.out.println(newEvent);	
		Event req = eventService.updateEvent(newEvent);
		//NotificationWrapper ntfWrapper = new NotificationWrapper();
		ntfWrapper.generateNotification(newEvent,"multiple");
		return req;
	}
	
	@PutMapping("/deleteevent")
	public void deleteEvent(@RequestBody Event newEvent) {
		System.out.println(newEvent);
		eventService.deleteEvent(newEvent);
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
	
	@PutMapping("/geteventtitle")
	public List<String> getEventTitle(@RequestBody EventSearch eventSearch) {
		List<Event> req = eventService.getEventTitle(eventSearch);
		System.out.println(req);
		List<String> title = req.stream().map((event) -> event.getEventTitle()).collect(Collectors.toList());
		
		//String title=req.subList(0, 1).toString();
		System.out.println(req.size());
		System.out.println(title);
		return title;
	}
	
	private Event getDummyEventObj()
	{
		Event er = Event.buildEvent(null, "Dummy Event Title", "Dummy Event Desc", new Date(), "1 hour",
				"Pune",
				"Sandhya", (long) 123456788, "Add Line One", "Add Line Two", "Add Line Three", "Camp", "Pune",
				411014, "Maharashtra", "India", false, "Draft", null, "Chinmay",
				"Manjunath", new Date(), new Date(), null, null, null, null, null, new Date());
		return er;
	}

}
