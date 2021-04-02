package org.iskon.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.iskon.models.NotificationApproval;
import org.iskon.models.NotificationUi;


public interface NotificationApprovalJpaRepository extends JpaRepository<NotificationApproval,Integer>{
	

	@Modifying
	@Query("update NotificationApproval n set n.action = :status, n.updatedBy = :userId where n.uuid = :notificationId")
	int setNotificationStatus(String status,String notificationId,int userId);
	
	@Query(
	  " SELECT new org.iskon.models.NotificationUi(n.uuid,n.message,n.targetType,n.targetId,n.createdBy,n.createdTime,n.updatedBy,n.updatedTime,n.action, n.id) FROM " 
	+ " NotificationApproval n, NotificationTracker as ntf_trk, User u"
	+ " where n.uuid = ntf_trk.notificationId  and ntf_trk.userId = u.id and u.email = :username"
	+ " ORDER BY n.createdTime DESC")
	List<NotificationUi> findByUserName(String username);
		
	@Query("Select new org.iskon.models.NotificationUi(n.uuid,n.message,n.targetType,n.targetId,n.createdBy,n.createdTime,n.updatedBy,n.updatedTime,n.action, n.id) from NotificationApproval n where n.uuid = :uuid and n.createdBy = :username")
	NotificationUi findByUuid(String uuid,String username);

	@Query("Select n from NotificationApproval n where n.uuid = :uuid")
	NotificationApproval findNotificationApprovalByUuid(String uuid);
	
	NotificationApproval findById(int id);

}
