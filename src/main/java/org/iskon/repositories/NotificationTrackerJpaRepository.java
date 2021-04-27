package org.iskon.repositories;

import java.util.List;

import org.iskon.models.NotificationTracker;
import org.iskon.models.NotificationUi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationTrackerJpaRepository extends JpaRepository<NotificationTracker, Integer>{
	@Query(	  " SELECT new org.iskon.models.NotificationTracker(n.userId, n.targetId, n.notificationId, n.targetTeamId) FROM " 
			+ "NotificationTracker as n"
			+ " where n.targetId = :id and n.notificationId = :uuid" )	
	List<NotificationTracker> findByEventId(String uuid, int id);
	
//	@Query(	  " SELECT n.userId FROM " 
//			+ "NotificationTracker as n"
//			+ " where n.notificationId = :uuid" )	
//	List<Integer> getUserIds(String uuid, int id);
}

