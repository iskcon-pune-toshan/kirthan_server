package org.iskon.repositories;


import org.iskon.models.EventTeamUser;
import org.iskon.models.TeamUser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTeamUserJpaRepository extends JpaRepository<EventTeamUser, Integer> {
    List<EventTeamUser> findAll(Specification<EventTeamUser> eventSpecification);
    
	@Query(" SELECT new org.iskon.models.EventTeamUser(ETU.id, ETU.userId, ETU.teamId, ETU.eventId, ETU.createdBy, ETU.updatedBy, ETU.createdTime, ETU.updatedTime, T.teamTitle, U.userName, E.eventTitle) "
			+ " FROM EventTeamUser ETU, Event E, Team T, User U "
			+ " where ETU.teamId = T.id " 
			+ " and ETU.eventId = E.id "
			+ " and ETU.userId = U.id " )	
	List<EventTeamUser> findAllWithDescription();    
    
}
