package org.iskon.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.iskon.models.EventRequest;
//import org.iskon.models.UserRequest;
import org.iskon.repositories.EventRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventRequestServiceImpl implements EventRequestService {

	EventRequestRepository eventRequestRepository;
	
	@Autowired
	public EventRequestServiceImpl(EventRequestRepository eventRequestRepository)
	{
		this.eventRequestRepository = eventRequestRepository;
	}

	@Override
	public EventRequest submitNewEventRequest(EventRequest eventRequest) 
	{
		return this.eventRequestRepository.submitNewEventRequest(eventRequest);
	}
	
	@Override
	public EventRequest submitUpdateEventRequest(EventRequest eventRequest) 
	{
		return this.eventRequestRepository.submitUpdateEventRequest(eventRequest);
	}

	@Override
	public EventRequest submitDeleteEventRequest(EventRequest eventRequest) 
	{
		return this.eventRequestRepository.submitDeleteEventRequest(eventRequest);
	}
	
	@Override
	public List<Integer> getEventRequestsCountByStatus(){
		return this.eventRequestRepository.getEventRequestsCountByStatus();
	}
	@Override
	public List<EventRequest> getEventRequests(Map<String, Object> query) {
		return this.eventRequestRepository.getEventRequests(query);
	}

	@Override
	public Boolean processEventRequest(Map<String,Object> params) {
		// validate input
		// Obtain user from current principal after security is implemented
		Boolean result = this.eventRequestRepository.processEventRequest((Integer)params.get("id"), 
				(String)params.get("approvalstatus"), (String)params.get("approvalcomments"), (String)params.get("usertype"), "Srinivas");
		if(result)
		{
			// Send Email
		}
		
		return result;
	}
}
