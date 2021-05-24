package org.iskon.repositories;

import java.util.Date;
import java.util.List;
import org.iskon.models.Notification;
import org.iskon.models.NotificationApproval;
import org.iskon.models.NotificationUi;
import org.iskon.models.Team;
import org.iskon.models.Event;
import org.iskon.models.EventUser;
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
	
	//Ntf goes to team lead & not local admin(Trial query)
	@Query("select DISTINCT userId from UserTemple where roleId = 4 ")
	List<Integer> getAdminId();
	
	@Query("select ntf_trk.targetTeamId from NotificationTracker as ntf_trk where ntf_trk.notificationId = :uuid and ntf_trk.userId = :id")
	Integer getTargetTeamId(String uuid, int id);
	
	@Query("select ntf_trk.targetTeamId from NotificationTracker as ntf_trk where ntf_trk.notificationId = :uuid")
	List<Integer> getTargetTeams(String uuid);
	
	//Ntf goes to selected localAdmin while team creation 
		@Query("select DISTINCT id from User u where u.fullName = :localAdminName ")
		List<Integer> getLocalAdminId(String localAdminName);
		
	
//	Geting Team id based on params ( Check event Time approaches)
	// and (team.startTime <= :eventTime) and (team.endTime >= :eventTime)
	@Query("Select t.id "+"from Team t "+"where t.category = :eventType and t.location = :eventCity and ( t.duration is NULL or t.duration>=:eventDuration ) and ( t.requestAcceptance is NULL or t.requestAcceptance = :daysBetween )")
	List<Integer> getTeamId(String eventType, String eventCity, Integer eventDuration, Integer daysBetween);
	
	//Get team lead mail based on params
	@Query("Select t.teamLeadId "+"from Team t "+"where t.id = :teamId")
	String getTeamLead(Integer teamId);
	
	//For cancel invite
	@Query("Select t.teamId "+"from EventTeam t "+"where t.eventId = :eventId")
	Integer getTeamLeadId(int eventId);

		
	@Query("SELECT new org.iskon.models.NotificationUi(n.uuid,n.message,n.targetType,n.targetId,n.createdBy,n.createdTime,n.updatedBy,n.updatedTime, n.id) FROM Notification n WHERE n.uuid = :ntfId and n.createdBy = :username")
	NotificationUi findByUuid(String ntfId,String username);
}
