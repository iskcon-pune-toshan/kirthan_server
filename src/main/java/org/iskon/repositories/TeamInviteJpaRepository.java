package org.iskon.repositories;

import java.util.List;

import org.iskon.models.NotificationTracker;
import org.iskon.models.NotificationUi;
import org.iskon.models.TeamInvite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamInviteJpaRepository extends JpaRepository<TeamInvite, Integer>{
	@Query(	  " SELECT new org.iskon.models.TeamInvite(n.id, n.eventId, n.notificationId, n.teamId, n.isProcessed) FROM " 
			+ "TeamInvite as n"
			+ " where n.eventId = :id and n.notificationId = :uuid" )	
	List<TeamInvite> findByEventId(String uuid, Integer id);
	
	@Query("Select new org.iskon.models.TeamInvite(n.id, n.eventId, n.notificationId, n.teamId, n.isProcessed) from TeamInvite n where n.notificationId = :uuid and n.teamId = :teamId")
	TeamInvite findByUuid(String uuid, Integer teamId);
	
	TeamInvite findById(int id);
}

