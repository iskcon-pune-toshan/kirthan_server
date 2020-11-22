package org.iskon.services;

import java.util.List;

import org.iskon.models.UserToken;
import org.iskon.repositories.UserTokenJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenServiceImpl {
	@Autowired
	UserTokenJpaRepository userTokenDb;
	
	public List<String> getDeviceTokenById(int userId){
		return userTokenDb.findDeviceTokenByUserId(userId);
	}
	
	public void deleteDeviceToken(int userId,String deviceToken) {
		userTokenDb.deleteDeviceTokenById(userId,deviceToken);
	}
	
	public void saveUserToken(UserToken userToken) {
		userTokenDb.save(userToken);
	}
	
	public List<UserToken> getDeviceTokenByUsername(String username){
		return userTokenDb.findDeviceTokenByUserName(username);
	}
	
	public void updateTokenByUserId(String username,String token) {
		 userTokenDb.updateTokenByUserId(username,token);
	}
}
