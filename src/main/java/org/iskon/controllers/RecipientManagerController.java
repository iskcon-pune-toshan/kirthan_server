package org.iskon.controllers;

import java.util.Map;

import org.iskon.models.NotificationListModel;
import org.iskon.models.UserTokenModel;
import org.iskon.repositories.NotificationListRepository;
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
	private NotificationListRepository ntfListDb;
	
	@Autowired
	private UserTokenRepository userTokenDb;
	
	@PostMapping(path = "/subscribe/{eventId}")
	public String subscribeToEvents(
			@RequestBody Map<String, Object> body,
			@PathVariable("eventId") int eventId) {
		NotificationListModel user = new NotificationListModel((int) body.get("userId"), eventId,(String) body.get("userType"));
		ntfListDb.addUser(user);
		return "Subscribed to event";
	}
	
	@GetMapping(path="/subscription")
	public String getSubscription() {
		return "Subscription list";
	}
	
	@PostMapping(path= "/unsubscribe/{eventId}")
	public String unSubscribeEvents(@RequestBody Map<String,Object> body,@PathVariable("eventId") int eventId) {
		body.put("groupId",eventId);
		return "Unsubscribed to event" + (ntfListDb.removeUser(body).toString());
	}
	@GetMapping(path="/tokens")
	public String getToken(@RequestBody Map<String,Object> body) {
		
		return userTokenDb.fetchDeviceToken((int) body.get("userId"));
	
	}
	//Step 1 for receiving notifications
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
	
	@PutMapping(path="/tokens")
	public void updateToken(@RequestBody Map<String,Object> body) {
		 int userId= (int) body.get("userId");
		 String token = (String) body.get("deviceToken");
		 userTokenDb.updateToken(userId,token);
	}
}
