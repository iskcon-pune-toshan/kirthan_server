package org.iskon.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.iskon.authentication.JwtUtil;
import org.iskon.models.Event;
import org.iskon.models.Notification;
import org.iskon.models.NotificationApproval;
import org.iskon.models.Team;
import org.iskon.models.User;
import org.iskon.services.EventService;
import org.iskon.services.NotificationService;
import org.iskon.services.TeamService;
import org.iskon.services.UserService;
import org.iskon.utils.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

	@Autowired
	private NotificationService ntfs;

	@Autowired
	private EventService eventService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private UserService userService;

	/**
	 * Fetches all the notifications associated with a given userId.
	 * Requires user to be an authenticated user.
	 * Implementation : User authentication needs to be added.
	 * @param User Json like object containing details that are required to process the request.In the current implementation that would be the userId.Example:{"userId" : "4"}
	 * @return response Json like object consisting of a message body that stores all the notifications in an array.
	 * @throws HttpException User-Built exception that generates an errorCode corresponding to the httpStatusCode.
	 *
	 */
	private String getJwt(String authorizationHeader) {
		JwtUtil jwtUtil = new JwtUtil();
		String jwt = authorizationHeader.replace("Bearer ", "");
		return jwtUtil.extractUsername(jwt);
		
	}
	@GetMapping()
	public ResponseEntity<Map<String,Object>> fetchNotificaion(HttpServletRequest request) throws HttpException {
		String jwtUsername = getJwt(request.getHeader("Authorization"));
		System.out.println(jwtUsername);
		Map<String,Object> response = new HashMap<>();
		System.out.println("This was called");
		HttpStatus respCode;
		try {
		
			response = ntfs.getAll( jwtUsername );
			respCode = HttpStatus.OK;
		}
		catch(RuntimeException err) {
			err.printStackTrace();
			response.put("message",err.getMessage());
			respCode = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		catch(Exception err) {
			response.put("message",err.getMessage());
			respCode = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<>(response,respCode);
	}


	/**
	 * Stores a notification before sending it to the specified recipient<br>
	 * <p>Incoming Body requirements: {
		    "userId": "4",
		    "message":"Testing notification",
		    "type" : "info",
		    "targetId": 4,
		    "targetType": "single"
				}</p>
	 * @param body json formatted data that should contain all the details required for the notification to be send.
	 * @return response json formatted body containing the status of the operation.
	 * @throws HttpException thows an exception with errCode and errMessage corresponding to the httpStatus code
	 */
	@PostMapping
	public ResponseEntity<Map<String, Object>> saveNotification(
			@RequestBody Notification data,@RequestHeader("Authorization") String authHeader) throws HttpException {
		String username = getJwt(authHeader);
		Map<String, Object> response = new HashMap<>();
		HttpStatus respCode = HttpStatus.OK;
		try {
			data.setCreatedBy(username);
			int targetId = data.getTargetId();
			data.setUuid(UUID.randomUUID());
			data.setCreatedTime(new Date());
			if (targetId == 0) {
				throw new HttpException("Incorrect Target id",
						HttpStatus.FORBIDDEN);
			}
			Boolean result = ntfs.saveNotification(data);
			if(result)
				response.put("status","Saved" );
			else{
				response.put("status","Failed");
			}
		} catch (RuntimeException err) {
			response.put("message", "Something went wrong");
			err.printStackTrace();
		} catch (HttpException err) {
			response.put("message", err.getMessage());
			respCode = err.getStatusCode();
		} catch (Exception err) {
			response.put("message",err.getMessage());
			respCode = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(response, respCode);
	}

	@PostMapping(path="/getApproval")
	public ResponseEntity<Map<String,Object>> saveNotificationAppr(
			@RequestBody NotificationApproval ntfa,
			@RequestHeader("Authorization") String authHeader){
		String username = getJwt(authHeader);
		Map<String, Object> response = new HashMap<>();
		HttpStatus respCode = HttpStatus.OK;
		try {
			ntfa.setCreatedBy(username);
			ntfa.setUuid(UUID.randomUUID());	
			ntfa.setCreatedTime(new Date());
			int targetId = ntfa.getTargetId();
			if (targetId <0) {
				throw new HttpException("Incorrect Target id",
						HttpStatus.FORBIDDEN);
			}
			Boolean result = ntfs.saveNotificationAppr(ntfa);
			if(result)
				response.put("status","Saved" );
			else{
				response.put("status","Failed");
			}
		} catch (RuntimeException err) {
			response.put("message", "Oops!Something went wrong");
			err.printStackTrace();
		} catch (HttpException err) {
			response.put("message", err.getMessage());
			respCode = err.getStatusCode();
		} catch (Exception err) {
			response.put("message","Sorry!Something went wrong");
			respCode = HttpStatus.INTERNAL_SERVER_ERROR;
		}

		return new ResponseEntity<>(response, respCode);
	}
//	/** Method not allowed
//	 * @return <b>HttpStatusCode</b>: 403 <b>Message</b>:Not allowed
//	 */
//	@PutMapping
//	public ResponseEntity<Map<String, Object>> updateNofifications() {
//		Map<String, Object> response = new HashMap<>();
//		HttpStatus respCode = HttpStatus.METHOD_NOT_ALLOWED;
//		response.put("message", "Oops!Method Not allowed");
//		return new ResponseEntity<>(response, respCode);
//	}
//
//	/**Method not allowed
//	 * @return <b>HttpStatus</b>:403
//	 */
//	@DeleteMapping
//	public ResponseEntity<String> deleteNotification() {
//		return new ResponseEntity<>(" Method Not Allowed", HttpStatus.FORBIDDEN);
//	}
//
//
//	/**
//	 * Fetches the detailed information stored in a database about a notification given the notificationId.
//	 * @param body Stores all the inforamtion received from the request body. The request Body should be a json object.It should contain all
//	 * the information required for a user to be authenticated
//	 * @param ntfId PathVariable thats equal to the notificationId.
//	 * @return response Map consisting of the notification details if a match is found and null other wise.
//	 * @throws HttpException throws exception with corresponding Http Status code and error message
//	 */
//
	@GetMapping(path="/one")
	public ResponseEntity<Map<String,Object>> fetchNtfById(
			@RequestBody Map<String,Object> body,
			@RequestHeader("Authorization") String authHeader) throws HttpException {
		String ntfId = (String) body.get("ntfId");
		String username = getJwt(authHeader);
		Map<String, Object> response = new HashMap<>();
		HttpStatus respCode;
		try {
			if ( ntfId == null)
				throw new Exception("Invalid Credentials");
			response.put("message", ntfs.getOne(ntfId,username));
			respCode = HttpStatus.OK;
		} catch (RuntimeException err) {
			response.put("message", err.getMessage());
			respCode = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (Exception e) {
			response.put("message", e.getMessage());
			respCode = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Map<String,Object>>(response, respCode);
	}
	


//	/**Method Not allowed
//	 *
//	 * @return this method is not allowed
//	 */
//	@PostMapping(path="/{ntfId}")
//	public ResponseEntity<Map<String,Object>> saveNotificationById(){
//		Map<String,Object> resp = new HashMap<>();
//			resp.put("message","Method Not Allowed");
//		return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.FORBIDDEN);
//	}
//
	
	/** Update the response to a approval seeking notification in the notification_approval table.
	 * 	Current statuses used : Approved,Rejected,Wait(Waiting for response)
	 * 	body : {"userId": integer, "response",Boolean} (response: 1 = Approved, 0=Rejected).
	 *
	 * @param body Stores all the information from the Http request body. Should consist of the notificationId and the response for the same.
	 * @return response returns a map with status information about the operation
	 */
	@PutMapping(path="/update")
	public ResponseEntity<Map<String,Object>> updateNotificationById(
			@RequestHeader("Authorization") String authHeader,
			@RequestBody Map<String,Object> body){
		String userName = getJwt(authHeader);
		String ntfId = (String) body.get("ntfId");
		Map<String,Object> response = new HashMap<>();
		HttpStatus respCode ;
		body.put("ntfId",ntfId);
		String status = "Rejected";
		if((int) body.get("response") == 1 ) status = "Approved";
		NotificationApproval updatedNtf = ntfs.updateApproval(status,ntfId,userName);
		Boolean resp = true;
		if(updatedNtf == null) resp = false; 
		if (updatedNtf.getTargetType().equalsIgnoreCase("event")) // this is for event
		{
			System.out.println("Called");
			
			  Event event = eventService.getEventById(updatedNtf.getTargetId());
			  event.setApprovalComments(updatedNtf.getAction());
			  event.setApprovalStatus(updatedNtf.getAction()); 
			  event.setUpdatedTime(new Date()); 
			  event.setUpdatedBy( "System"); 
			  event.setIsProcessed(true);
			  eventService.processEvent(event);
			}
		else if (updatedNtf.getTargetType().equalsIgnoreCase("team")) // this is for user
		{
			
			  Team team = teamService.getTeamById(updatedNtf.getTargetId());
			  team.setApprovalComments(updatedNtf.getAction());
			  team.setApprovalStatus(updatedNtf.getAction()); team.setIsProcessed(true);
			  team.setUpdatedBy("System");
			  team.setUpdatedTime(updatedNtf.getUpdatedTime());
			  teamService.processTeam(team);
			 		}
		else if (updatedNtf.getTargetType().equalsIgnoreCase("user")) // this is for team
		{
			
			  User userTarget = userService.getUserById(updatedNtf.getTargetId());
			  userTarget.setApprovalComments(updatedNtf.getAction());
			  userTarget.setApprovalStatus(updatedNtf.getAction()); 
			  userTarget.setIsProcessed(true);
			  userTarget.setUpdatedBy("System");
			  userTarget.setUpdatedTime(new Date());
			  userService.processUser(userTarget);
				}

		if(resp)respCode = HttpStatus.OK;
		else respCode = HttpStatus.BAD_REQUEST;
		return new ResponseEntity<>(response,respCode);
	}

//	/**To be implemented<br>
//	 * <b>Doubt</b>
//	 * <p>
//	 * 	Whether everyone should be allowed to delete notifications belonging to them or are only admin allowed to delete notifications
//	 * 	Or no one is allowed to delete notifications and notifications will be archived after a certain time period.
//	 * </p>
//	 * @return response status of the operation
//	 */
//	@DeleteMapping(path="/{ntfId}")
//	public String deleteNotificationById(){
//		//Depends on the app-policy
//		//to be implemented
//		return "Not implemented";
//	}
//

}

