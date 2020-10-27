package org.iskon.repositories;

import org.iskon.models.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserTokenJpaRepository extends JpaRepository<UserToken,Integer>{
	@Query(value = "select DISTINCT device_token from USER_TOKEN where user_id = ?1",nativeQuery = true)	
	String findDeviceTokenByUserId(int userId);
	
	@Modifying
	@Query(value = "UPDATE user_token set device_token = :token where user_id = :userId",nativeQuery = true)
	int updateTokenByUserId(int userId,String token);
}