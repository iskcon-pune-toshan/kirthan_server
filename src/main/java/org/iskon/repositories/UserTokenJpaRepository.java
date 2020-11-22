package org.iskon.repositories;

import java.util.List;

import org.iskon.models.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserTokenJpaRepository extends JpaRepository<UserToken,Integer>{
	
	@Query("select distinct deviceToken from UserToken where userId = :userId")	
	List<String> findDeviceTokenByUserId(int userId);
	
	@Modifying
	@Query("update UserToken u set u.deviceToken =:token where u.createdBy = :user ")
	int updateTokenByUserId(String user,String token);
	
	@Modifying
	@Query("delete from UserToken u where userId = :userId and deviceToken = :deviceToken")
	void deleteDeviceTokenById(int userId,String deviceToken);
	
	@Query("Select u from UserToken u where u.createdBy = :username")
	List<UserToken> findDeviceTokenByUserName(String username);
}