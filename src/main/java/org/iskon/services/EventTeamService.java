package org.iskon.services;

import java.util.List;
import java.util.Map;
import org.iskon.models.EventTeamSearch;
import org.iskon.models.NotificationApproval;
import org.iskon.models.Event;
import org.iskon.models.EventTeam;

public interface EventTeamService {

	EventTeam addEventTeam(Event event);
	
	Integer getTeamId(int id);

	void deleteEventTeam(EventTeam newEventTeamMapping);

	List<EventTeam> getEventTeams(EventTeamSearch query);
	
	List<EventTeam> getEventTeamsWithDescription();

	void addEventTeam(NotificationApproval updatefNtf);

}
