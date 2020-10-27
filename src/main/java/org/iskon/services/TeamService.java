package org.iskon.services;

import java.util.List;
import java.util.Map;

import org.iskon.models.Team;
import org.iskon.models.TeamSearch;
public interface TeamService {

    Team addTeam(Team teamRequest);

	List<Team> getTeams(TeamSearch query);

	Boolean processTeam(Team params);

	Team updateTeam(Team newTeamRequest);

	void deleteTeam(Team newTeamRequest);


}
