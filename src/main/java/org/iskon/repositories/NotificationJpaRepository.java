package org.iskon.repositories;

import java.util.List;
import org.iskon.models.Notification;
import org.iskon.models.NotificationApproval;
import org.iskon.models.NotificationUi;
import org.iskon.models.Team;
import org.iskon.models.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NotificationJpaRepository extends JpaRepository<Notification,Integer>{
	
	@Query(	  " SELECT new org.iskon.models.NotificationUi(n.uuid,n.message,n.targetType,n.targetId,n.createdBy,n.createdTime,n.updatedBy,n.updatedTime, n.id) FROM " 
			+ " Notification n, NotificationTracker as ntf_trk, User u"
			+ " where (n.uuid = ntf_trk.notificationId  and ntf_trk.userId = u.id and u.email = :username)"
			+ " ORDER BY n.createdTime DESC" )	
	List<NotificationUi> findByUserName(String username);
	
	@Query("select DISTINCT userId from EventUser where eventId = :eventId")
	List<Integer> getParticipants(int eventId);
	
	@Query("select DISTINCT userId from TeamUser where teamId = :teamId")
	List<Integer> getTeamMemberId(List<Integer> teamId);
	
	//Ntf goes to team lead & not local admin
	@Query("select DISTINCT userId from UserTemple where roleId = 4 ")
	List<Integer> getAdminId();
	
	//Geting TeamLead's user id based on params (TRIAL FUNCTION)
	@Query("Select t.teamLead "+"from Team t "+"where t.teamDescription = :eventType")
	List<Integer> getTeamLead(String eventType);
	
//	Geting TeamLead's user id based on params ( Complete function)
//	@Query("Select t.teamLead "+"from Team t "+"where t.teamDescription = :eventType and t.teamLocation = :location and (team.startTime <= :eventTime) and (team.endTime >= :eventTime) and t.teamWeekDay = :eventDay")
//	List<Integer> getTeamLead(String eventType, String eventLocation, Date eventTime, String eventDay);
	

	//For cancel invite
	@Query("Select t.teamId "+"from EventTeam t "+"where t.eventId = :eventId")
	Integer getTeamLeadId(int eventId);

		
	@Query("SELECT new org.iskon.models.NotificationUi(n.uuid,n.message,n.targetType,n.targetId,n.createdBy,n.createdTime,n.updatedBy,n.updatedTime, n.id) FROM Notification n WHERE n.uuid = :ntfId and n.createdBy = :username")
	NotificationUi findByUuid(String ntfId,String username);
}
