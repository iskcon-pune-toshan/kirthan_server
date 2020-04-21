package org.iskon.services;

import org.iskon.models.EventTeamUserMapping;
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
	


}
