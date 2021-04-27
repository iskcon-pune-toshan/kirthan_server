package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.EventTeamUser;
import org.iskon.models.EventTeamUserSearch;
import org.iskon.models.TeamUser;

public interface EventTeamUserService {

	List<EventTeamUser> addEventTeamUser(List<EventTeamUser> listEventTeamUser);

	void deleteEventTeamUser(List<EventTeamUser> listEventTeamUser);

	List<EventTeamUser> getEventTeamUsers(EventTeamUserSearch query);
	
	List<EventTeamUser> getEventTeamUsersByUserName(String username);
	
	List<EventTeamUser> getEventTeamUsersWithDescription();

}
