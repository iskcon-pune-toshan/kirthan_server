package org.iskon.controllers;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.iskon.models.EventRequest;
//import org.iskon.models.UserRequests;
import org.iskon.services.EventRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EventRequestController {

	@Autowired
	EventRequestService eventRequestService;
	
	@RequestMapping(value = "/getdummyeventrequest", method = RequestMethod.GET)
	public List<EventRequest> getDummyEventRequest() { 		
		List<EventRequest> userreqs = new ArrayList<EventRequest>();
		EventRequest req = getDummyEventRequestObj();	
		userreqs.add(req);
		//userreqs.add(req);
		return userreqs;
	}
	
	@RequestMapping(value = "/submitneweventrequest", method = RequestMethod.PUT)
	public EventRequest submitNewEventRequest(@RequestBody EventRequest newEventRequest) {
		EventRequest req = eventRequestService.submitNewEventRequest(newEventRequest);
		return req;
	}
	
	@RequestMapping(value = "/geteventrequests", method = RequestMethod.PUT)
	public List<EventRequest> getEventRequests(@RequestBody Map<String,Object> queryParams) {
		//System.out.println("geteventrequests");
		List<EventRequest> req = eventRequestService.getEventRequests(queryParams);
		return req;
	}
	
	@RequestMapping(value = "/processeventrequest", method = RequestMethod.PUT)
	public Boolean processeventrequest(@RequestBody Map<String,Object> params) {
		Boolean req = eventRequestService.processEventRequest(params);
		return req;
	}
	
	private EventRequest getDummyEventRequestObj()
	{
		EventRequest er = new EventRequest();
		er.setPhoneNumber(8007774787L);
		er.setAddLineOne("Flat no 20, Kalyani A");
		er.setAddLineTwo("Aditya Garden City");
		er.setLocality("Warje");
		er.setCity("Pune");
		er.setState("Maharastra");
		er.setCountry("India");
		er.setPinCode(411058);
		er.setIsProcessed(false);
		er.setCreatedBy("Srinivas");
		er.setCreateTime(new Date());
		er.setEventId(1);
		er.setUpdatedBy("Manjunath");
		er.setUpdateTime(new Date());
		er.setAddLineThree("Add Line three");
		er.setApprovalStatus("Draft");
		return er;
	}
}
