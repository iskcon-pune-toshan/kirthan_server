package org.iskon.controllers;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.iskon.models.TeamUserMapping;
import org.iskon.services.TeamUserMappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class TeamUserMappingController {

	@Autowired
	TeamUserMappingService teamUserMappingService;
	
	
/*	
	@RequestMapping(value = "/submitnewteamusermapping", method = RequestMethod.PUT)
	public TeamUserMapping submitNewTeamUserMapping(@RequestBody TeamUserMapping newTeamUserMapping) {
		System.out.println(newTeamUserMapping);
		TeamUserMapping req = teamUserMappingService.submitNewTeamUserMapping(newTeamUserMapping);
		return req;
	}
*/	
	@RequestMapping(value = "/submitnewteamusermapping", method = RequestMethod.PUT)
	public List<TeamUserMapping> submitNewTeamUserMapping(@RequestBody List<TeamUserMapping> listTeamUserMapping) {
		List<TeamUserMapping> listNewTeamUserMapping = new ArrayList<TeamUserMapping>();
		for (TeamUserMapping newTeamUserMapping : listTeamUserMapping) {
			System.out.println(newTeamUserMapping);
			TeamUserMapping req = teamUserMappingService.submitNewTeamUserMapping(newTeamUserMapping);
			listNewTeamUserMapping.add(req);
		}
		return listNewTeamUserMapping;
		
	}
	
	@RequestMapping(value = "/submitdeleteteamusermapping", method = RequestMethod.PUT)
	public TeamUserMapping submitDeleteTeamUserMapping(@RequestBody TeamUserMapping newTeamUserMapping) {
		System.out.println(newTeamUserMapping);
		TeamUserMapping req = teamUserMappingService.submitDeleteTeamUserMapping(newTeamUserMapping);
		return req;
	}
	
	@RequestMapping(value = "/getteamusermappings", method = RequestMethod.PUT)
	public List<TeamUserMapping> getTeamUserMappings(@RequestBody Map<String,Object> queryParams) {
		//System.out.println("queryParams: "+queryParams);
		List<TeamUserMapping> req = teamUserMappingService.getTeamUserMappings(queryParams);
		return req;
	}	
	
	
}
