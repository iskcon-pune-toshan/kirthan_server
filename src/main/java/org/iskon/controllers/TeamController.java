package org.iskon.controllers;

import java.util.List;
import java.util.Map;
import org.iskon.models.TeamSearch;
import org.iskon.models.Team;
import org.iskon.services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/team")
public class TeamController {

	@Autowired
	private TeamService teamService;


	@RequestMapping(value = "/addteam", method = RequestMethod.PUT)
	public Team addTeam(@RequestBody Team newTeam) {
		Team req = teamService.addTeam(newTeam);
//		NotificationWrapper nw = new NotificationWrapper();
//		nw.populateTeamNotification(req);
		return req;
	}

	@RequestMapping(value = "/updateteam", method = RequestMethod.PUT)
	public Team updateTeam(@RequestBody Team newTeam) {
		System.out.println(newTeam);
		Team req = teamService.updateTeam(newTeam);
//		NotificationWrapper nw = new NotificationWrapper();
//		nw.populateTeamNotification(req);
		return req;
	}

	@RequestMapping(value = "/deleteteam", method = RequestMethod.PUT)
	public void deleteTeam(@RequestBody Team newTeam) {
		System.out.println(newTeam);
	    teamService.deleteTeam(newTeam);
//		NotificationWrapper nw = new NotificationWrapper();
//		nw.populateTeamNotification(req);
		
	}

	@RequestMapping(value = "/getteam", method = RequestMethod.PUT)
	public List<Team> getTeam(@RequestBody TeamSearch queryParams) {
		List<Team> req = teamService.getTeams(queryParams);
		return req;
	}

	@RequestMapping(value = "/processteam", method = RequestMethod.PUT)
	public Boolean processTeam(@RequestBody Team params) {
		System.out.println("in teamrequestcontroller: "+params);
		Boolean req = teamService.processTeam(params);
		return req;
	}



}
