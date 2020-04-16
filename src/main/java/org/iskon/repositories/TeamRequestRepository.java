package org.iskon.repositories;

import java.util.List;
import java.util.Map;

import org.iskon.models.TeamRequest;

public interface TeamRequestRepository {

	TeamRequest submitNewTeamRequest(TeamRequest newTeamRequest);
	
	List<TeamRequest> getTeamRequests(Map<String,Object> query);
	
	Boolean processTeamRequest(Integer id, String approvalstatus, String approvalcomments, String updatedby);
	
	TeamRequest submitUpdateTeamRequest(TeamRequest teamRequest);

	TeamRequest submitDeleteTeamRequest(TeamRequest teamRequest);


}