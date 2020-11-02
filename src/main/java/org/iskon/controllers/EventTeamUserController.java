package org.iskon.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.iskon.models.Event;
import org.iskon.models.EventTeamUser;
import org.iskon.services.EventTeamUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.iskon.models.EventTeamUserSearch;
import org.iskon.models.TeamUser;


@RestController
@RequestMapping("/api/eventteamuser")
public class EventTeamUserController {

	@Autowired
	private EventTeamUserService eventTeamUserMappingService;

	@RequestMapping(value = "/addeventteamuser", method = RequestMethod.PUT)
	public EventTeamUser addEventTeamUser(@RequestBody EventTeamUser listEventTeamUserMapping) {
//		List<EventTeamUser> listNewEventTeamUserMapping = new ArrayList<EventTeamUser>();
//		for (EventTeamUser newEventTeamUserMapping : listEventTeamUserMapping) {
//			System.out.println(newEventTeamUserMapping);
//			EventTeamUser req = eventTeamUserMappingService.addEventTeamUser(newEventTeamUserMapping);
//			listNewEventTeamUserMapping.add(req);
//		}
		EventTeamUser req = eventTeamUserMappingService.addEventTeamUser(listEventTeamUserMapping);
		return req;
	}

	@RequestMapping(value = "/geteventteamusers", method = RequestMethod.PUT)
	public List<EventTeamUser> getEventTeamUsers(@RequestBody EventTeamUserSearch queryParams) {
		//System.out.println("queryParams: "+queryParams);
		List<EventTeamUser> req = eventTeamUserMappingService.getEventTeamUsers(queryParams);
		return req;
	}


	@RequestMapping(value = "/deleteeventteamuser", method = RequestMethod.PUT)
	public void deleteEventTeamUser(@RequestBody EventTeamUser listEventTeamUserMapping) {
		eventTeamUserMappingService.deleteEventTeamUser(listEventTeamUserMapping);
		//List<EventTeamUser> listDeleteEventTeamUserMapping = new ArrayList<EventTeamUser>();
//		for (EventTeamUser deleteEventTeamUserMapping : listEventTeamUserMapping) {
//			System.out.println(deleteEventTeamUserMapping);
//			EventTeamUser req = eventTeamUserMappingService.deleteEventTeamUser(deleteEventTeamUserMapping);
//			listDeleteEventTeamUserMapping.add(req);
//		}
		//return listDeleteEventTeamUserMapping;
	}
	
	@RequestMapping(value = "/geteventteamuserswithdescription", method = RequestMethod.PUT)
	public List<EventTeamUser> getEventTeamUsersWithDescription() {
		//System.out.println("queryParams: "+queryParams);
		List<EventTeamUser> req =eventTeamUserMappingService.getEventTeamUsersWithDescription();
		return req;
	}
	

}
