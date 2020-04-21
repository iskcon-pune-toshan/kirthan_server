package org.iskon.controllers;

import java.util.List;
import java.util.ArrayList;

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
	
	
/*	
	@RequestMapping(value = "/submitnewteamusermapping", method = RequestMethod.PUT)
	public TeamUserMapping submitNewTeamUserMapping(@RequestBody TeamUserMapping newTeamUserMapping) {
		System.out.println(newTeamUserMapping);
		TeamUserMapping req = teamUserMappingService.submitNewTeamUserMapping(newTeamUserMapping);
		return req;
	}
*/	
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
	
	
	
	@RequestMapping(value = "/submitdeleteeventteamusermapping", method = RequestMethod.PUT)
	public EventTeamUserMapping submitDeleteEventTeamUserMapping(@RequestBody EventTeamUserMapping newEventTeamUserMapping) {
		System.out.println(newEventTeamUserMapping);
		EventTeamUserMapping req = eventTeamUserMappingService.submitDeleteEventTeamUserMapping(newEventTeamUserMapping);
		return req;
	}	
	
}
