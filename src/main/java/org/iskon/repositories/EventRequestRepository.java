package org.iskon.repositories;

import java.util.List;
import java.util.Map;

import org.iskon.models.EventRequest;

import com.google.common.base.Optional;

public interface EventRequestRepository {

	EventRequest submitNewEventRequest(EventRequest newEventRequest);
	List<Integer> getEventRequestsCountByStatus();
	List<EventRequest> getEventRequests(Map<String,Object> query);
	
	Boolean processEventRequest(Integer id, String approvalstatus, String approvalcomments, String usertype, String updatedby);

	EventRequest submitUpdateEventRequest(EventRequest eventRequest);

	EventRequest submitDeleteEventRequest(EventRequest eventRequest);

}
