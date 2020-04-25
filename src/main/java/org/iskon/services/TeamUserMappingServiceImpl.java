package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.TeamUserMapping;
import org.iskon.repositories.TeamUserMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeamUserMappingServiceImpl implements TeamUserMappingService {

	TeamUserMappingRepository teamUserMappingRepository;
	
	@Autowired
	public TeamUserMappingServiceImpl(TeamUserMappingRepository teamUserMappingRepository)
	{
		this.teamUserMappingRepository = teamUserMappingRepository;
	}

	@Override
	public TeamUserMapping submitNewTeamUserMapping(TeamUserMapping teamUserMapping) 
	{
		return this.teamUserMappingRepository.submitNewTeamUserMapping(teamUserMapping);
	}
	

	@Override
	public TeamUserMapping submitDeleteTeamUserMapping(TeamUserMapping teamUserMapping) 
	{
		return this.teamUserMappingRepository.submitDeleteTeamUserMapping(teamUserMapping);
	}
	
	@Override
	public List<TeamUserMapping> getTeamUserMappings(Map<String, Object> query) {
		
		return this.teamUserMappingRepository.getTeamUserMappings(query);
	}

	

	
}
