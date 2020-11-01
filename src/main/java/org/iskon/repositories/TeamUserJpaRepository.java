package org.iskon.repositories;


import org.iskon.models.Notification;
import org.iskon.models.TeamUser;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamUserJpaRepository extends JpaRepository<TeamUser, Integer> {
    List<TeamUser> findAll(Specification<TeamUser> eventSpecification);
    
	@Query(
			" SELECT TU.id, TU.userId, TU.teamId, TU.createdBy, TU.updatedBy, TU.createdTime, TU.updatedTime, T.teamTitle, U.userName " 
			+ " FROM TeamUser TU, Team T, User U " 
			//+ " where TU.teamId= :teamId " 
			+ " where TU.teamId = T.id " 
			+ " and TU.userId = U.id " )	
	List<TeamUser> findAllWithDescription();    
}
