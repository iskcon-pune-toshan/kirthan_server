package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.EventTeamUserMapping;
import org.iskon.models.TeamUserMapping;
import org.iskon.repositories.EventTeamUserMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventTeamUserMappingServiceImpl implements EventTeamUserMappingService{
	

	EventTeamUserMappingRepository eventTeamUserMappingRepository;
	
	@Autowired
	public EventTeamUserMappingServiceImpl(EventTeamUserMappingRepository eventTeamUserMappingRepository)
	{
		this.eventTeamUserMappingRepository = eventTeamUserMappingRepository;
	}

	@Override
	public EventTeamUserMapping submitNewEventTeamUserMapping(EventTeamUserMapping eventTeamUserMapping) 
	{
		return this.eventTeamUserMappingRepository.submitNewEventTeamUserMapping(eventTeamUserMapping);
	}
	

	@Override
	public EventTeamUserMapping submitDeleteEventTeamUserMapping(EventTeamUserMapping eventTeamUserMapping) 
	{
		return this.eventTeamUserMappingRepository.submitDeleteEventTeamUserMapping(eventTeamUserMapping);
	}
	
	
	@Override
	public List<EventTeamUserMapping> getEventTeamUserMappings(Map<String, Object> query) {
		
		return this.eventTeamUserMappingRepository.getEventTeamUserMappings(query);
	}
	


}
