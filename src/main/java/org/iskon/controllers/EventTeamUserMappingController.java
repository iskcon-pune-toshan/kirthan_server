package org.iskon.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.iskon.models.EventTeamUserMapping;
import org.iskon.services.EventTeamUserMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class EventTeamUserMappingController {

	@Autowired
	EventTeamUserMappingService eventTeamUserMappingService;
	
	@RequestMapping(value = "/submitneweventteamusermapping", method = RequestMethod.PUT)
	public List<EventTeamUserMapping> submitNewEventTeamUserMapping(@RequestBody List<EventTeamUserMapping> listEventTeamUserMapping) {
		List<EventTeamUserMapping> listNewEventTeamUserMapping = new ArrayList<EventTeamUserMapping>();
		for (EventTeamUserMapping newEventTeamUserMapping : listEventTeamUserMapping) {
			System.out.println(newEventTeamUserMapping);
			EventTeamUserMapping req = eventTeamUserMappingService.submitNewEventTeamUserMapping(newEventTeamUserMapping);
			listNewEventTeamUserMapping.add(req);
		}
		return listNewEventTeamUserMapping;
	}
	
	@RequestMapping(value = "/geteventteamusermappings", method = RequestMethod.PUT)
	public List<EventTeamUserMapping> getEventTeamUserMappings(@RequestBody Map<String,Object> queryParams) {
		//System.out.println("queryParams: "+queryParams);
		List<EventTeamUserMapping> req = eventTeamUserMappingService.getEventTeamUserMappings(queryParams);
		return req;
	}	
	
	
	@RequestMapping(value = "/submitdeleteeventteamusermapping", method = RequestMethod.PUT)
	public List<EventTeamUserMapping> submitDeleteEventTeamUserMapping(@RequestBody List<EventTeamUserMapping> listEventTeamUserMapping) {
		List<EventTeamUserMapping> listDeleteEventTeamUserMapping = new ArrayList<EventTeamUserMapping>();
		for (EventTeamUserMapping deleteEventTeamUserMapping : listEventTeamUserMapping) {
			System.out.println(deleteEventTeamUserMapping);
			EventTeamUserMapping req = eventTeamUserMappingService.submitDeleteEventTeamUserMapping(deleteEventTeamUserMapping);
			listDeleteEventTeamUserMapping.add(req);
		}
		return listDeleteEventTeamUserMapping;
	}	
	
}
