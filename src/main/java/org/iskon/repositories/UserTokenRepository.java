package org.iskon.repositories;

import java.util.List;

import org.iskon.models.UserTokenModel;

public interface UserTokenRepository {
	
	public String fetchDeviceToken(int userId);
	public List<String> fetchDeviceToken(List<Integer> userId);
	public Boolean storeTokens(UserTokenModel user);
	public Boolean updateToken(int userId,String token);

}
