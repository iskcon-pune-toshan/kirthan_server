package org.iskon.repositories;

import org.iskon.models.UserTokenModel;

public interface UserTokenRepository {
	
	public String fetchDeviceToken(int userId);
	public Boolean storeTokens(UserTokenModel user);
	public Boolean updateToken(int userId,String token);

}
