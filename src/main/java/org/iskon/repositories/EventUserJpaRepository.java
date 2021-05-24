package org.iskon.repositories;


import org.iskon.models.EventUser;
import org.iskon.models.TeamUser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventUserJpaRepository extends JpaRepository<EventUser, Integer> {
    List<EventUser> findAll(Specification<EventUser> eventSpecification);
    
	@Query(" SELECT new org.iskon.models.EventUser(ETU.id, ETU.eventId, ETU.userId, ETU.createdBy, ETU.updatedBy, ETU.createdTime, ETU.updatedTime, ETU.userName) "
			+ " FROM EventUser ETU, Event E, Team T, User U "
			+ " where " 
			+ " ETU.eventId = E.id "
			+ " and ETU.userId = U.id " )	
	List<EventUser> findAllWithDescription();    
	
	@Query(" SELECT new org.iskon.models.EventUser(ETU.id, ETU.eventId, ETU.userId, ETU.createdBy, ETU.updatedBy, ETU.createdTime, ETU.updatedTime, ETU.userName) "
			+ " FROM EventUser ETU"
			+ " where ETU.userName = :username" 
			)	
	List<EventUser> findByUserName(String username);    
    
}
