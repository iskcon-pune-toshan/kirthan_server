package org.iskon.controllers;

import java.util.Date;
import java.util.Map;

import org.iskon.models.UserToken;
import org.iskon.repositories.NotificationJpaRepository;
import org.iskon.repositories.UserTokenJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipientManagerController {

	@Autowired
	private UserTokenJpaRepository userTokenDb;
	

	
	/** Fetches the stored device token for the provided userId
	 * 	
	 * @param body user information required to authenticate/(uniquely identify) the user.
	 * @return token A string containing the token for the given userID
	 */
	@GetMapping(path="/tokens")
	public String getToken(@RequestBody Map<String,Object> body) {
		
		return userTokenDb.findDeviceTokenByUserId((int) body.get("userId"));
	
	}
	/**
	 * 
	 * @param body contains the userId,deviceToken,firebaseUid provided in the request body
	 */
	@PostMapping(path="/tokens")
	public void storeTokens(@RequestBody Map<String,Object> body) {
		System.out.println("Store procedure called");
		/*UserTokenModel user = new UserTokenModel(
						(int)body.get("userId"),
						(String)body.get("deviceToken"),
						(String) body.get("firebaseUid"),
						(int)body.get("userId"),
						(int)body.get("userId"));
		UserToken user = new UserToken(4,(String) body.get("deviceToken"),"randomUid",4,4);*/
		UserToken user = UserToken.buildUserToken(null, (int)body.get("userId"), (String)body.get("deviceToken"), (String)(body.get("firebaseUid")),
				(int)body.get("userId"),(int) body.get("userId"),new Date(),new Date());
		userTokenDb.save(user);
	}
	
	/**Updates the deviceToken for a given userID.
	 * 
	 * @param body Contains the userId and deviceToken provided in the incoming httpRequest.
	 */
	@Transactional 
	@PutMapping(path="/tokens")
	public void updateToken(@RequestBody Map<String,Object> body) {
		 int userId= 4;
		 String token = (String) body.get("deviceToken");
		 userTokenDb.updateTokenByUserId(userId,token);
	}
}
