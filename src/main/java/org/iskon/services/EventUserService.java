package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.EventUser;
import org.iskon.models.EventUserSearch;
import org.iskon.models.TeamUser;

public interface EventUserService {

	List<EventUser> addEventTeamUser(List<EventUser> listEventTeamUser);

	void deleteEventTeamUser(List<EventUser> listEventTeamUser);

	List<EventUser> getEventTeamUsers(EventUserSearch query);
	
	List<EventUser> getEventTeamUsersByUserName(String username);
	
	List<EventUser> getEventTeamUsersWithDescription();

}
