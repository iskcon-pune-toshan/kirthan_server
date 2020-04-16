package org.iskon.repositories;

import java.util.List;
import java.util.Map;

import org.iskon.models.EventRequest;

public interface EventRequestRepository {

	EventRequest submitNewEventRequest(EventRequest newEventRequest);
	
	List<EventRequest> getEventRequests(Map<String,Object> query);
	
	Boolean processEventRequest(Integer id, String approvalstatus, String approvalcomments, String usertype, String updatedby);

	EventRequest submitUpdateEventRequest(EventRequest eventRequest);

	EventRequest submitDeleteEventRequest(EventRequest eventRequest);

}
