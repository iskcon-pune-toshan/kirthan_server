package org.iskon.services;

import java.util.List;
import java.util.Map;
import org.iskon.models.TeamUserSearch;
import org.iskon.models.TeamUser;

public interface TeamUserService {

	TeamUser addTeamUser(TeamUser newTeamUserMapping);

	void deleteTeamUser(TeamUser newTeamUserMapping);

	List<TeamUser> getTeamUsers(TeamUserSearch query);
	
	List<TeamUser> getTeamUsersWithDescription();

}
