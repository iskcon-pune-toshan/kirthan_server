package org.iskon.repositories;



import org.iskon.models.EventTeamUserMapping;

public interface EventTeamUserMappingRepository {
	
	
    EventTeamUserMapping submitNewEventTeamUserMapping(EventTeamUserMapping newEventTeamUserMapping);
	
	EventTeamUserMapping submitDeleteEventTeamUserMapping(EventTeamUserMapping newEventTeamUserMapping);

	
	
}
