package org.iskon.services;

import java.util.List;
import java.util.Map;
import org.iskon.models.TeamUserSearch;
import org.iskon.models.TeamUser;

public interface TeamUserService {

	List<TeamUser> addTeamUser(List<TeamUser> listTeamUser);

	void deleteTeamUser(List<TeamUser> newTeamUserMapping);

	List<TeamUser> getTeamUsers(TeamUserSearch query);
	
	List<TeamUser> getTeamUsersWithDescription();

}
