package org.iskon.services;

import java.util.List;

import org.iskon.models.Event;
import org.iskon.models.EventSearch;


public interface EventService {

	Event addEvent(Event event);
	
	Event getEventById(int id);

	List<Event> getEvents(EventSearch event);
	
	Boolean processEvent(Event event);

	Event updateEvent(Event event);

	void deleteEvent(Event event);
	
	List<Event> getEventTitle(EventSearch event);
	
}

