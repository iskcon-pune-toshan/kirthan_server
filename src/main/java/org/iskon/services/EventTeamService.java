package org.iskon.services;

import java.util.List;
import java.util.Map;
import org.iskon.models.EventTeamSearch;
import org.iskon.models.EventTeam;

public interface EventTeamService {

	List<EventTeam> addEventTeam(List<EventTeam> listEventTeam);

	void deleteEventTeam(EventTeam newEventTeamMapping);

	List<EventTeam> getEventTeams(EventTeamSearch query);
	
	List<EventTeam> getEventTeamsWithDescription();

}
