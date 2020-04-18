package org.iskon.controllers;

import java.util.List;
import java.util.Map;

import org.iskon.models.TeamRequest;
import org.iskon.models.UserRequest;
import org.iskon.services.TeamRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TeamRequestController {
	
	@Autowired
	TeamRequestService teamRequestService;
	
	
	@RequestMapping(value = "/submitnewteamrequest", method = RequestMethod.PUT)
	public TeamRequest submitNewTeamRequest(@RequestBody TeamRequest newTeamRequest) {
		TeamRequest req = teamRequestService.submitNewTeamRequest(newTeamRequest);
		return req;
	}
	
	@RequestMapping(value = "/submitupdateteamrequest", method = RequestMethod.PUT)
	public TeamRequest submitUpdateTeamRequest(@RequestBody TeamRequest newTeamRequest) {
		System.out.println(newTeamRequest);
		TeamRequest req = teamRequestService.submitUpdateTeamRequest(newTeamRequest);
		return req;
	}
	
	@RequestMapping(value = "/submitdeleteteamrequest", method = RequestMethod.PUT)
	public TeamRequest submitDeleteTeamRequest(@RequestBody TeamRequest newTeamRequest) {
		System.out.println(newTeamRequest);
		TeamRequest req = teamRequestService.submitDeleteTeamRequest(newTeamRequest);
		return req;
	}	
	
	@RequestMapping(value = "/getteamrequests", method = RequestMethod.PUT)
	public List<TeamRequest> getTeamRequests(@RequestBody Map<String,Object> queryParams) {
		List<TeamRequest> req = teamRequestService.getTeamRequests(queryParams);
		return req;
	}
	
	@RequestMapping(value = "/processteamrequest", method = RequestMethod.PUT)
	public Boolean processteamrequest(@RequestBody Map<String,Object> params) {
		System.out.println("in teamrequestcontroller: "+params);
		Boolean req = teamRequestService.processTeamRequest(params);
		return req;
	}
	
	

}
