package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.EventTeamUser;
import org.iskon.models.EventTeamUserSearch;
import org.iskon.models.TeamUser;

public interface EventTeamUserService {

	EventTeamUser addEventTeamUser(EventTeamUser newEventTeamUserMapping);

	void deleteEventTeamUser(EventTeamUser newEventTeamUserMapping);

	List<EventTeamUser> getEventTeamUsers(EventTeamUserSearch query);
	
	List<EventTeamUser> getEventTeamUsersWithDescription();

}
