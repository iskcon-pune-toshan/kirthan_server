package org.iskon.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.iskon.models.EventRequest;


public interface EventRequestService {

	EventRequest submitNewEventRequest(EventRequest eventRequest);
	List<Integer> getEventRequestsCountByStatus();
	List<EventRequest> getEventRequests(Map<String,Object> query);
	
	Boolean processEventRequest(Map<String,Object> params);

	EventRequest submitUpdateEventRequest(EventRequest newEventRequest);

	EventRequest submitDeleteEventRequest(EventRequest newEventRequest);
	
}

