package org.iskon.controllers;

import java.util.List;

import org.iskon.authentication.JwtUtil;
import org.iskon.models.EventUser;
import org.iskon.models.EventUserSearch;
import org.iskon.services.EventUserService;
import org.iskon.utils.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/eventuser")
public class EventUserController {

	@Autowired
	private EventUserService eventTeamUserMappingService;

	@RequestMapping(value = "/addeventuser", method = RequestMethod.PUT)
	public List<EventUser> addEventTeamUser(@RequestBody List<EventUser> listEventTeamUser) {
//		List<EventTeamUser> listNewEventTeamUserMapping = new ArrayList<EventTeamUser>();
//		for (EventTeamUser newEventTeamUserMapping : listEventTeamUserMapping) {
//			System.out.println(newEventTeamUserMapping);
//			EventTeamUser req = eventTeamUserMappingService.addEventTeamUser(newEventTeamUserMapping);
//			listNewEventTeamUserMapping.add(req);
//		}
		List<EventUser> req = eventTeamUserMappingService.addEventTeamUser(listEventTeamUser);
		return req;
		
	}
	
	private String getJwt(String authorizationHeader) {
		JwtUtil jwtUtil = new JwtUtil();
		String jwt = authorizationHeader.replace("Bearer ", "");
		return jwtUtil.extractUsername(jwt);
		
	}

	@RequestMapping(value = "/geteventusers", method = RequestMethod.GET)
	public List<EventUser> getEventTeamUsers(@RequestHeader("Authorization") String authHead) throws HttpException {
		//System.out.println("queryParams: "+queryParams);
		List<EventUser> req = eventTeamUserMappingService.getEventTeamUsersByUserName(getJwt(authHead));
		return req;
	}


	@RequestMapping(value = "/deleteeventuser", method = RequestMethod.PUT)
	public List<EventUser> deleteEventTeamUser(@RequestBody List<EventUser> listEventTeamUser) {
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
	
	@RequestMapping(value = "/geteventuserswithdescription", method = RequestMethod.PUT)
	public List<EventUser> getEventTeamUsersWithDescription() {
		//System.out.println("queryParams: "+queryParams);
		List<EventUser> req =eventTeamUserMappingService.getEventTeamUsersWithDescription();
		return req;
	}
	

}
