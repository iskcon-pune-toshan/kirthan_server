package org.iskon.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import org.iskon.models.Event;
import org.iskon.models.EventTeam;
import org.iskon.services.EventTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.iskon.models.EventTeamSearch;


@RestController
@RequestMapping("/api/eventteam")
public class EventTeamController {

	@Autowired
	private EventTeamService EventTeamMappingService;

/*
	@RequestMapping(value = "/submitnewteamusermapping", method = RequestMethod.PUT)
	public TeamUserMapping submitNewTeamUserMapping(@RequestBody TeamUserMapping newTeamUserMapping) {
		System.out.println(newTeamUserMapping);
		TeamUserMapping req = teamUserMappingService.submitNewTeamUserMapping(newTeamUserMapping);
		return req;
	}
*/
	@RequestMapping(value = "/addeventteam", method = RequestMethod.PUT)
	public EventTeam addEventTeam(@RequestBody Event listTeamUser) {
		EventTeam res = EventTeamMappingService.addEventTeam(listTeamUser);
		
		return res;
		
		/*
		 * List<TeamUser> listNewTeamUserMapping = new ArrayList<TeamUser>(); for
		 * (TeamUser newTeamUserMapping : listTeamUserMapping) {
		 * System.out.println(newTeamUserMapping); 
		 * TeamUser req =
		 * teamUserMappingService.addTeamUser(newTeamUserMapping);
		 * listNewTeamUserMapping.add(req); } //TeamUser
		 * tr=teamUserMappingService.addTeamUser(listTeamUserMapping); return
		 * listNewTeamUserMapping;
		 */
	}

	@RequestMapping(value = "/deleteeventteam", method = RequestMethod.PUT)
	public void deleteTeamUser(@RequestBody EventTeam listTeamUser) {
		EventTeamMappingService.deleteEventTeam(listTeamUser);
		 
	
		/*
		 * //teamUserMappingService.deleteTeamUser(listTeamUserMapping); List<TeamUser>
		 * listDeleteTeamUserMapping = new ArrayList<TeamUser>(); for (TeamUser
		 * deleteTeamUserMapping : listTeamUserMapping) {
		 * System.out.println(deleteTeamUserMapping);
		 * teamUserMappingService.deleteTeamUser(deleteTeamUserMapping);
		 * //listDeleteTeamUserMapping.add(req); } return listTeamUserMapping;
		 */	}

	@RequestMapping(value = "/geteventteams", method = RequestMethod.PUT)
	public List<String> getEventTeams(@RequestBody EventTeamSearch queryParams) {
		//System.out.println("queryParams: "+queryParams);
		List<EventTeam> req =EventTeamMappingService.getEventTeams(queryParams);
		List<String> title = req.stream().map((event) -> event.getTeamName()).collect(Collectors.toList());
		
		return title;
	}

	@RequestMapping(value = "/geteventteamswithdescription", method = RequestMethod.PUT)
	public List<EventTeam> getTeamUsersWithDescription() {
		//System.out.println("queryParams: "+queryParams);
		List<EventTeam> req =EventTeamMappingService.getEventTeamsWithDescription();
		return req;
	}



}
