package org.iskon.repositories;

import java.util.List;
import java.util.Map;

import org.iskon.models.TeamUserMapping;

public interface TeamUserMappingRepository {

	TeamUserMapping submitNewTeamUserMapping(TeamUserMapping newTeamUserMapping);
	
	TeamUserMapping submitDeleteTeamUserMapping(TeamUserMapping newTeamUserMapping);
	
}
