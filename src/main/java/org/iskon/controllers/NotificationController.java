package org.iskon.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.iskon.authentication.JwtUtil;
import org.iskon.models.Event;
import org.iskon.models.Notification;
import org.iskon.models.NotificationApproval;
import org.iskon.models.NotificationUi;
import org.iskon.models.Team;
import org.iskon.models.User;
import org.iskon.services.EventService;
import org.iskon.services.NotificationService;
import org.iskon.services.TeamService;
import org.iskon.services.UserService;
import org.iskon.utils.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	public List<NotificationUi> fetchNotificaion(@RequestHeader("Authorization") String authHead) throws HttpException {
		return ntfs.getAll(getJwt(authHead));
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
	public Boolean saveNotification(
			@RequestBody Notification data,@RequestHeader("Authorization") String authHeader) throws HttpException {
		String username = getJwt(authHeader);
		data.setCreatedBy(username);
		data.setUuid(UUID.randomUUID());
		data.setCreatedTime(new Date());
		return ntfs.saveNotification(data);
	}

	@PostMapping(path="/getApproval")
	public Boolean saveNotificationAppr(
			@RequestBody NotificationApproval ntfa,
			@RequestHeader("Authorization") String authHeader){
			String username = getJwt(authHeader);
			ntfa.setCreatedBy(username);
			ntfa.setUuid(UUID.randomUUID());	
			ntfa.setCreatedTime(new Date());
			return ntfs.saveNotificationAppr(ntfa);			
	}

//	/**
//	 * Fetches the detailed information stored in a database about a notification given the notificationId.
//	 * @param body Stores all the inforamtion received from the request body. The request Body should be a json object.It should contain all
//	 * the information required for a user to be authenticated
//	 * @param ntfId PathVariable thats equal to the notificationId.
//	 * @return response Map consisting of the notification details if a match is found and null other wise.
//	 * @throws HttpException throws exception with corresponding Http Status code and error message
//	 */
//
	@GetMapping(path="/getSingleNotification")
	public NotificationUi fetchNtfById(
			@RequestBody Map<String,Object> body,
			@RequestHeader("Authorization") String authHeader) throws HttpException {
		String ntfId = (String) body.get("ntfId");
		String username = getJwt(authHeader);
		return ntfs.getOne(ntfId,username);	
	}
	

	/** Update the response to a approval seeking notification in the notification_approval table.
	 * 	Current statuses used : Approved,Rejected,Wait(Waiting for response)
	 * 	body : {"userId": integer, "response",Boolean} (response: 1 = Approved, 0=Rejected).
	 *
	 * @param body Stores all the information from the Http request body. Should consist of the notificationId and the response for the same.
	 * @return response returns a map with status information about the operation
	 */
	@PutMapping(path="/update")
	public Boolean updateNotificationById(
			@RequestHeader("Authorization") String authHeader,
			@RequestBody Map<String,Object> body){
		String userName = getJwt(authHeader);
		String ntfId = (String) body.get("ntfId");
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

		return resp;
	}

}

