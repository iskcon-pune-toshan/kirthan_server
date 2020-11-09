package org.iskon.controllers;

import java.util.Date;
import java.util.Map;

import org.iskon.authentication.JwtUtil;
import org.iskon.models.UserToken;
import org.iskon.repositories.UserTokenJpaRepository;
import org.iskon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipientManagerController {

	@Autowired
	private UserTokenJpaRepository userTokenDb;
	
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
	public String getToken(@RequestBody Map<String,Object> body) {
		return userTokenDb.findDeviceTokenByUserId((int) body.get("userId"));
	}
	
	/**
	 * @param body contains the userId,deviceToken,firebaseUid provided in the request body
	 */
	
	@PostMapping(path="/tokens")
	public void storeTokens(@RequestHeader("Authorization") String authHeader,@RequestBody Map<String,Object> body) {
		String username = authHeader.replace("Bearer ", "");
		username = jwtUtil.extractUsername(username);
		int userID = userService.getUserByEmailId(username).get().getId();
		UserToken user = UserToken.buildUserToken(null, userID , (String)body.get("deviceToken"), (String)(body.get("firebaseUid")),
				username, username, new Date(), new Date());
		userTokenDb.save(user);
	}
	
	/**Updates the deviceToken for a given userID.
	 * @param body Contains the userId and deviceToken provided in the incoming httpRequest.
	 */
	@Transactional 
	@PutMapping(path="/tokens")
	public int updateToken(@RequestBody Map<String,Object> body,@RequestHeader("Authorization") String authHeader) {
		String username = authHeader.replace("Bearer ", "");
		username = jwtUtil.extractUsername(username);
		System.out.println(username);
		String token = (String) body.get("deviceToken");
		return userTokenDb.updateTokenByUserId(username,token);
	}
}
