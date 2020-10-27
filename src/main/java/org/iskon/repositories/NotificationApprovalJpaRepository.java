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
	+ " NotificationApproval n "
	+ " LEFT JOIN NotificationTracker as ntf_trk "
	+ "	ON n.uuid = ntf_trk.notificationId"
	+ " where ntf_trk.userId = :userId "
	+ " ORDER BY n.createdTime DESC")
	List<NotificationApproval> findByUserId(int userId);
	
	@Query("Select  n from NotificationApproval n where n.uuid = :uuid")
	NotificationApproval findByUuid(String uuid);
}
