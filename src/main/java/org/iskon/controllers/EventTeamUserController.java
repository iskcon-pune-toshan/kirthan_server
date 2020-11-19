package org.iskon.controllers;

import java.util.List;

import org.iskon.models.EventTeamUser;
import org.iskon.models.EventTeamUserSearch;
import org.iskon.services.EventTeamUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/eventteamuser")
public class EventTeamUserController {

	@Autowired
	private EventTeamUserService eventTeamUserMappingService;

	@RequestMapping(value = "/addeventteamuser", method = RequestMethod.PUT)
	public List<EventTeamUser> addEventTeamUser(@RequestBody List<EventTeamUser> listEventTeamUser) {
//		List<EventTeamUser> listNewEventTeamUserMapping = new ArrayList<EventTeamUser>();
//		for (EventTeamUser newEventTeamUserMapping : listEventTeamUserMapping) {
//			System.out.println(newEventTeamUserMapping);
//			EventTeamUser req = eventTeamUserMappingService.addEventTeamUser(newEventTeamUserMapping);
//			listNewEventTeamUserMapping.add(req);
//		}
		List<EventTeamUser> req = eventTeamUserMappingService.addEventTeamUser(listEventTeamUser);
		return req;
	}

	@RequestMapping(value = "/geteventteamusers", method = RequestMethod.PUT)
	public List<EventTeamUser> getEventTeamUsers(@RequestBody EventTeamUserSearch queryParams) {
		//System.out.println("queryParams: "+queryParams);
		List<EventTeamUser> req = eventTeamUserMappingService.getEventTeamUsers(queryParams);
		return req;
	}


	@RequestMapping(value = "/deleteeventteamuser", method = RequestMethod.PUT)
	public List<EventTeamUser> deleteEventTeamUser(@RequestBody List<EventTeamUser> listEventTeamUser) {
		//System.out.println("Delete Event Team: "+listEventTeamUser.size());
		eventTeamUserMappingService.deleteEventTeamUser(listEventTeamUser);
		return listEventTeamUser;
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
