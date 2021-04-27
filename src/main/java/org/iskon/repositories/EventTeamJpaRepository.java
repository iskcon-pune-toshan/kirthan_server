package org.iskon.repositories;


import org.iskon.models.Notification;
import org.iskon.models.EventTeam;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventTeamJpaRepository extends JpaRepository<EventTeam, Integer> {
    List<EventTeam> findAll(Specification<EventTeam> eventSpecification);
    
	@Query(" SELECT new org.iskon.models.EventTeam(TU.id, TU.eventId, TU.teamId, TU.createdBy, TU.updatedBy, TU.createdTime, TU.updatedTime, T.teamTitle, U.eventTitle) "
			+ " FROM EventTeam TU, Team T, Event U "
			+ " where TU.teamId = T.id " 
			+ " and TU.eventId = U.id " )	
	List<EventTeam> findAllWithDescription();  
	
	@Query(" SELECT TU.teamId"
			+ " FROM EventTeam TU"
			+ " WHERE TU.eventId = :id " )	
	Integer getTeamId(int id);  
	
	
}
