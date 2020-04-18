package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.TeamUserMapping;

public interface TeamUserMappingService {
	
	TeamUserMapping submitNewTeamUserMapping(TeamUserMapping newTeamUserMapping);
	
	TeamUserMapping submitDeleteTeamUserMapping(TeamUserMapping newTeamUserMapping);

}
