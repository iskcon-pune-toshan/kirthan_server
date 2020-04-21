package org.iskon.services;

import org.iskon.models.TeamUserMapping;

public interface TeamUserMappingService {
	
	TeamUserMapping submitNewTeamUserMapping(TeamUserMapping newTeamUserMapping);
	
	TeamUserMapping submitDeleteTeamUserMapping(TeamUserMapping newTeamUserMapping);

}
