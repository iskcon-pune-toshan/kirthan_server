package org.iskon.controllers;

import java.util.Map;

import org.iskon.models.UserTokenModel;
import org.iskon.repositories.NotificationRepository;
import org.iskon.repositories.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
public class RecipientManagerController {
	
	@Autowired
	private NotificationRepository ntfDb;	
	@Autowired
	private UserTokenRepository userTokenDb;
	

	
	/** Fetches the stored device token for the provided userId
	 * 	
	 * @param body user information required to authenticate/(uniquely identify) the user.
	 * @return token A string containing the token for the given userID
	 */
	@GetMapping(path="/tokens")
	public String getToken(@RequestBody Map<String,Object> body) {
		
		return userTokenDb.fetchDeviceToken((int) body.get("userId"));
	
	}
	/**
	 * 
	 * @param body contains the userId,deviceToken,firebaseUid provided in the request body
	 */
	@PostMapping(path="/tokens")
	public void storeTokens(@RequestBody Map<String,Object> body) {
		UserTokenModel user = new UserTokenModel(
						(int)body.get("userId"),
						(String)body.get("deviceToken"),
						(String) body.get("firebaseUid"),
						(int)body.get("userId"),
						(int)body.get("userId"));
		userTokenDb.storeTokens(user);				
	}
	
	/**Updates the deviceToken for a given userID.
	 * 
	 * @param body Contains the userId and deviceToken provided in the incoming httpRequest.
	 */
	@PutMapping(path="/tokens")
	public void updateToken(@RequestBody Map<String,Object> body) {
		 int userId= (int) body.get("userId");
		 String token = (String) body.get("deviceToken");
		 userTokenDb.updateToken(userId,token);
	}
}
