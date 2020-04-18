package org.iskon.controllers;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.iskon.models.TeamUserMapping;
//import org.iskon.models.TeamRequest;
//import org.iskon.models.UserRequests;
//import org.iskon.services.UserRequestService;
import org.iskon.services.TeamUserMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TeamUserMappingController {

	@Autowired
	TeamUserMappingService TeamUserMappingService;
	
	
	
	@RequestMapping(value = "/submitteamusermapping", method = RequestMethod.PUT)
	public TeamUserMapping submitNewTeamUserMapping(@RequestBody TeamUserMapping newTeamUserMapping) {
		System.out.println(newTeamUserMapping);
		TeamUserMapping req = TeamUserMappingService.submitNewTeamUserMapping(newTeamUserMapping);
		return req;
	}
	
	
	@RequestMapping(value = "/submitdeleteteamusermapping", method = RequestMethod.PUT)
	public TeamUserMapping submitDeleteTeamUserMapping(@RequestBody TeamUserMapping newTeamUserMapping) {
		System.out.println(newTeamUserMapping);
		TeamUserMapping req = TeamUserMappingService.submitDeleteTeamUserMapping(newTeamUserMapping);
		return req;
	}	
	
}
