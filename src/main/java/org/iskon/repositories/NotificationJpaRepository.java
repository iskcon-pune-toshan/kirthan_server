package org.iskon.repositories;

import java.util.List;
import org.iskon.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationJpaRepository extends JpaRepository<Notification,Integer>{
	
	@Query(
	  "	SELECT n FROM " 
	+ " Notification n "
	+ " LEFT JOIN NotificationTracker as ntf_trk "
	+ "	ON n.uuid = ntf_trk.notificationId"
	+ " where ntf_trk.userId = :userId "
	+ " ORDER BY n.createdTime DESC")	
	List<Notification> findByUserId(int userId);
	
	//currentlyOnlyfetching the event mapping;
	@Query(value = "select DISTINCT user_id from event_user where eventId = ?1",nativeQuery = true)
	List<Integer> getParticipants(int eventId);
	
	@Query(value = "select DISTINCT user_id from team_user where teamId = ?1",nativeQuery = true)
	List<Integer> getTeamMemberId(List<Integer> teamId);
	
	@Query("SELECT n FROM Notification n WHERE uuid = :ntfId")
	Notification findByNotificationId(String ntfId);
}
