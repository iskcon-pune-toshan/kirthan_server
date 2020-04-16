package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.TeamRequest;

public interface TeamRequestService {
	
    TeamRequest submitNewTeamRequest(TeamRequest teamRequest);
	
	List<TeamRequest> getTeamRequests(Map<String,Object> query);
	
	Boolean processTeamRequest(Map<String,Object> params);
	
	TeamRequest submitUpdateTeamRequest(TeamRequest newTeamRequest);

	TeamRequest submitDeleteTeamRequest(TeamRequest newTeamRequest);


}
