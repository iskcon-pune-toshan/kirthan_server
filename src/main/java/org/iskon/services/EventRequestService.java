package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.EventRequest;


public interface EventRequestService {

	EventRequest submitNewEventRequest(EventRequest eventRequest);
	
	List<EventRequest> getEventRequests(Map<String,Object> query);
	
	Boolean processEventRequest(Map<String,Object> params);
	
}

