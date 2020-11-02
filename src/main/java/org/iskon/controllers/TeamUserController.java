package org.iskon.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.iskon.models.Event;
import org.iskon.models.TeamUser;
import org.iskon.services.TeamUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.iskon.models.TeamUserSearch;


@RestController
@RequestMapping("/api/teamuser")
public class TeamUserController {

	@Autowired
	private TeamUserService teamUserMappingService;


/*
	@RequestMapping(value = "/submitnewteamusermapping", method = RequestMethod.PUT)
	public TeamUserMapping submitNewTeamUserMapping(@RequestBody TeamUserMapping newTeamUserMapping) {
		System.out.println(newTeamUserMapping);
		TeamUserMapping req = teamUserMappingService.submitNewTeamUserMapping(newTeamUserMapping);
		return req;
	}
*/
	@RequestMapping(value = "/addteamuser", method = RequestMethod.PUT)
	public TeamUser addTeamUser(@RequestBody TeamUser listTeamUserMapping) {
//		List<TeamUser> listNewTeamUserMapping = new ArrayList<TeamUser>();
//		for (TeamUser newTeamUserMapping : listTeamUserMapping) {
//			System.out.println(newTeamUserMapping);
//			TeamUser req = teamUserMappingService.addTeamUser(newTeamUserMapping);
//			listNewTeamUserMapping.add(req);
//		}
		TeamUser tr=teamUserMappingService.addTeamUser(listTeamUserMapping);
		return tr;

	}

	@RequestMapping(value = "/deleteteamuser", method = RequestMethod.PUT)
	public void deleteTeamUser(@RequestBody TeamUser listTeamUserMapping) {
		teamUserMappingService.deleteTeamUser(listTeamUserMapping);
//		List<TeamUser> listDeleteTeamUserMapping = new ArrayList<TeamUser>();
//		for (TeamUser deleteTeamUserMapping : listTeamUserMapping) {
//			System.out.println(deleteTeamUserMapping);
//			TeamUser req = teamUserMappingService.deleteTeamUser(deleteTeamUserMapping);
//			listDeleteTeamUserMapping.add(req);
//		}
//		return listDeleteTeamUserMapping;
	}

	@RequestMapping(value = "/getteamusers", method = RequestMethod.PUT)
	public List<TeamUser> getTeamUsers(@RequestBody TeamUserSearch queryParams) {
		//System.out.println("queryParams: "+queryParams);
		List<TeamUser> req =teamUserMappingService.getTeamUsers(queryParams);
		return req;
	}

	@RequestMapping(value = "/getteamuserswithdescription", method = RequestMethod.PUT)
	public List<TeamUser> getTeamUsersWithDescription() {
		//System.out.println("queryParams: "+queryParams);
		List<TeamUser> req =teamUserMappingService.getTeamUsersWithDescription();
		return req;
	}



}
