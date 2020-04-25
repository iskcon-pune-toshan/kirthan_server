package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.TeamRequest;
import org.iskon.repositories.TeamRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamRequestServiceImpl implements TeamRequestService {

    TeamRequestRepository teamRequestRepository;
	
	@Autowired
	public TeamRequestServiceImpl(TeamRequestRepository teamRequestRepository)
	{
		this.teamRequestRepository = teamRequestRepository;
	}

	@Override
	public TeamRequest submitNewTeamRequest(TeamRequest teamRequest) 
	{
		return this.teamRequestRepository.submitNewTeamRequest(teamRequest);
	}
	
	@Override
	public TeamRequest submitUpdateTeamRequest(TeamRequest teamRequest) 
	{
		return this.teamRequestRepository.submitUpdateTeamRequest(teamRequest);
	}

	@Override
	public TeamRequest submitDeleteTeamRequest(TeamRequest teamRequest) 
	{
		return this.teamRequestRepository.submitDeleteTeamRequest(teamRequest);
	}
	
	
	@Override
	public List<TeamRequest> getTeamRequests(Map<String, Object> query) {
		
		return this.teamRequestRepository.getTeamRequests(query);
	}

	@Override
	public Boolean processTeamRequest(Map<String,Object> params) {
		// validate input
		// Obtain user from current principal after security is implemented
		//System.out.println(params);
		Boolean result = this.teamRequestRepository.processTeamRequest((Integer)params.get("id"), 
				(String)params.get("approvalstatus"), (String)params.get("approvalcomments"),"manjunath");
		
		if(result)
		{
			// Send Email
		}
		
		return result;
	}

}