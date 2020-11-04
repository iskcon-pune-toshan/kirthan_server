package org.iskon.repositories;

import java.util.List;
import org.iskon.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationJpaRepository extends JpaRepository<Notification,Integer>{
	
	@Query(	  " SELECT n FROM " 
			+ " Notification n, NotificationTracker as ntf_trk, User u"
			+ " where n.uuid = ntf_trk.notificationId  and ntf_trk.userId = u.id and u.email = :username"
			+ " ORDER BY n.createdTime DESC")	
	List<Notification> findByUserName(String username);
	
	//currentlyOnlyfetching the event mapping;
	@Query("select DISTINCT userId from EventUser where eventId = :eventId")
	List<Integer> getParticipants(int eventId);
	
	@Query("select DISTINCT userId from TeamUser where teamId = :teamId")
	List<Integer> getTeamMemberId(List<Integer> teamId);
	
	@Query("SELECT n FROM Notification n WHERE n.uuid = :ntfId and n.createdBy = :username")
	Notification findByUuid(String ntfId,String username);
}
