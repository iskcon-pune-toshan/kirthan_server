package org.iskon.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.iskon.authentication.JwtUtil;
import org.iskon.models.UserToken;
import org.iskon.services.UserService;
import org.iskon.services.UserTokenServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTokenController {

	@Autowired
	private UserTokenServiceImpl userTokenService;
	
	@Autowired
	JwtUtil jwtUtil = new JwtUtil();

	@Autowired
	UserService userService;
	
	/** Fetches the stored device token for the provided userId
	 * 	
	 * @param body user information required to authenticate/(uniquely identify) the user.
	 * @return token A string containing the token for the given userID
	 */
	@GetMapping(path="/tokens")
	public List<UserToken> getToken(@RequestHeader("Authorization") String authHeader) {
		String username = authHeader.replace("Bearer ", "");
		username = jwtUtil.extractUsername(username);
		return userTokenService.getDeviceTokenByUsername(username);
	}
	
	/**
	 * @param body contains the userId,deviceToken,firebaseUid provided in the request body
	 */
	
	@PostMapping(path="/tokens")
	public void storeTokens(@RequestHeader("Authorization") String authHeader,@RequestBody UserToken userToken) {
			String username = authHeader.replace("Bearer ", "");
			username = jwtUtil.extractUsername(username);
			int userID = userService.getUserByEmailId(username).get().getId();
			userToken.setUserId(userID);
			userToken.setCreatedBy(username);
			userToken.setCreatedTime(new Date());
			userTokenService.saveUserToken(userToken);
	}
	
	/**Updates the deviceToken for a given userID.
	 * @param body Contains the userId and deviceToken provided in the incoming httpRequest.
	 * Modification needed before using
	 */
	@Transactional 
	@PutMapping(path="/tokens")
	public void updateToken(@RequestBody Map<String,Object> body,@RequestHeader("Authorization") String authHeader) {
		String username = authHeader.replace("Bearer ", "");
		username = jwtUtil.extractUsername(username);
		System.out.println(username);
		String token = (String) body.get("deviceToken");
		userTokenService.updateTokenByUserId(username,token);
	}
}
