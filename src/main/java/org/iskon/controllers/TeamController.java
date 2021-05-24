package org.iskon.controllers;

import java.util.List;
import java.util.Map;
import org.iskon.models.TeamSearch;
import org.iskon.models.TeamUser;
import org.iskon.models.User;
import org.iskon.models.EventUser;
import org.iskon.models.Team;
import org.iskon.services.TeamService;
import org.iskon.services.TeamUserService;
import org.iskon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/team")
public class TeamController {

	@Autowired
	private TeamService teamService;
	
	@Autowired
	private TeamUserService teamUserMappingService;

	@Autowired
	UserService userService;

	@Autowired
	private NotificationWrapper nw ;
	


	@RequestMapping(value = "/addteam",method = RequestMethod.PUT)
    public Team addTeam( @RequestBody Team newTeam) {

		Team req = teamService.addTeam(newTeam);
		List<TeamUser> res = req.getListOfTeamMembers();
		for(int i=0; i<res.size(); i++) {
			res.get(i).setTeamId(req.getId());
		}
		teamUserMappingService.addTeamUser(res);
		//NotificationWrapper nw = new NotificationWrapper();
		if(!newTeam.getApprovalStatus().equalsIgnoreCase("Approved")) {
		nw.generateNotification(req,"single");}
		else if(newTeam.getApprovalStatus().equalsIgnoreCase("Approved")) {
			User user = userService.getUserByEmailId(newTeam.getTeamLeadId()).get();
			if(user.getRoleId().equals(3))
				 user.setRoleId(4);
			nw.generateNotification(user, "team initiated");
		}
		return req;
    }

	@RequestMapping(value = "/updateteam", method = RequestMethod.PUT)
	public Team updateTeam(@RequestBody Team newTeam) {
		System.out.println(newTeam);
		Team req = teamService.updateTeam(newTeam);
		try {
			if(!req.getListOfTeamMembers().isEmpty()) {
				List<TeamUser> res = req.getListOfTeamMembers();
				for(int i=0; i<res.size(); i++) {
					res.get(i).setTeamId(req.getId());
				}
				teamUserMappingService.addTeamUser(res);
			}
		} catch(NullPointerException npe) {
			System.out.println("No value available");
		}
		
//		NotificationWrapper nw = new NotificationWrapper();
		nw.generateNotification(req,"multiple");
		return req;
	}

	@RequestMapping(value = "/deleteteam", method = RequestMethod.PUT)
	public void deleteTeam(@RequestBody Team newTeam) {
		System.out.println(newTeam);
		User user = userService.getUserByEmailId(newTeam.getTeamLeadId()).get();
		 if(user.getRoleId().equals(4))
			 user.setRoleId(3);
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