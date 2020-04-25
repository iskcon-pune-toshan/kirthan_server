package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.EventTeamUserMapping;

public interface EventTeamUserMappingService {

	EventTeamUserMapping submitNewEventTeamUserMapping(EventTeamUserMapping newEventTeamUserMapping);
	
	EventTeamUserMapping submitDeleteEventTeamUserMapping(EventTeamUserMapping newEventTeamUserMapping);
	
	List<EventTeamUserMapping> getEventTeamUserMappings(Map<String,Object> query);

}
