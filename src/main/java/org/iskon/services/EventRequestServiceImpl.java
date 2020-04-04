package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.EventRequest;

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
