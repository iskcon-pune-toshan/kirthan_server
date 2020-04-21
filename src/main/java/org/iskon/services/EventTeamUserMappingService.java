package org.iskon.services;

import org.iskon.models.EventTeamUserMapping;

public interface EventTeamUserMappingService {

	EventTeamUserMapping submitNewEventTeamUserMapping(EventTeamUserMapping newEventTeamUserMapping);
	
	EventTeamUserMapping submitDeleteEventTeamUserMapping(EventTeamUserMapping newEventTeamUserMapping);

}
