package org.iskon.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.iskon.models.NotificationApproval;


public interface NotificationApprovalJpaRepository extends JpaRepository<NotificationApproval,Integer>{
	

	@Modifying
	@Query("update NotificationApproval n set n.action = :status, n.updatedBy = :userId where n.uuid = :notificationId")
	int setNotificationStatus(String status,String notificationId,int userId);
	
	@Query(
	  " SELECT n FROM " 
	+ " NotificationApproval n, NotificationTracker as ntf_trk, User u"
	+ " where n.uuid = ntf_trk.notificationId  and ntf_trk.userId = u.id and u.email = :username"
	+ " ORDER BY n.createdTime DESC")
	List<NotificationApproval> findByUserName(String username);
		
	@Query("Select  n from NotificationApproval n where n.uuid = :uuid and n.createdBy = :username")
	NotificationApproval findByUuid(String uuid,String username);
}
