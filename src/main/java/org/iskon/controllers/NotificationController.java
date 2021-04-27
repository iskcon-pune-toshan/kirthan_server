package org.iskon.controllers;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.iskon.authentication.JwtUtil;
import org.iskon.models.Event;
import org.iskon.models.EventSearch;
import org.iskon.models.EventTeam;
import org.iskon.models.Notification;
import org.iskon.models.NotificationApproval;
import org.iskon.models.NotificationSearch;
import org.iskon.models.NotificationUi;
import org.iskon.models.Team;
import org.iskon.models.User;
import org.iskon.repositories.EventTeamJpaRepository;
import org.iskon.repositories.NotificationApprovalJpaRepository;
import org.iskon.services.EventService;
import org.iskon.services.EventTeamService;
import org.iskon.services.NotificationService;
import org.iskon.services.TeamService;
import org.iskon.services.UserService;
import org.iskon.utils.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {
	@Autowired
	private NotificationApprovalJpaRepository ntfApprovalDb;

	@Autowired
	private NotificationService ntfs;
	
	@Autowired
	private EventTeamService eventTeamService;

	@Autowired
	private EventService eventService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private UserService userService;

	private String getJwt(String authorizationHeader) {
		JwtUtil jwtUtil = new JwtUtil();
		String jwt = authorizationHeader.replace("Bearer ", "");
		return jwtUtil.extractUsername(jwt);
		
	}
	
	/**
	 * Fetches all the notifications associated with a given userId.
	 * Requires user to be an authenticated user.
	 * @param authHeader Authorization parameter should be added in the request header with values Bearer followed by the jwt token
	 * @return List(NotificationUI)  A list of notification objects modified to be UI compatible will be returned.
	 */
	
	@GetMapping()
	public List<NotificationUi> fetchNotificaion(@RequestHeader("Authorization") String authHead) throws HttpException {
		return ntfs.getAll(getJwt(authHead));
	}


	/**
	 * Stores a notification before sending it to the specified recipient<br>
	 * @param data Values in Json format corresponding to the notification model class.
	 * @param authHeader Authorization parameter should be added in the request header with values Bearer followed by the jwt token
	 * @return Boolean true or false returned corresponding to the operation's success or failure
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

	/**
	 * Stores a notification that requires an approval/reject action, before sending it to the user
	 * Stores a notification before sending it to the specified recipient<br>
	 * @param data Values in Json format corresponding to the notification model class.
	 * @param authHeader Authorization parameter should be added in the request header with values Bearer followed by the jwt token
	 * @return Boolean true or false returned corresponding to the operation's success or failure
	 */
	//Where is this used?
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
	

	/** Update the response to a approval seeking notification in the notification_approval table.
	 * 	Current statuses used : Approved,Rejected,Wait(Waiting for response)
	 * 	body : {"userId": integer, "response",Boolean} (response: 1 = Approved, 0=Rejected).
	 * @param body Stores all the information from the HTTP request body. Should consist of the notificationId and the response for the same.
	 * @return boolean returns a true or false value corresponding to the operations success or failure.
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
		if(updatedNtf.getTargetType().equalsIgnoreCase("event") && updatedNtf.getAction().equalsIgnoreCase("Approved")) {
			eventTeamService.addEventTeam(updatedNtf);
		}
		if (updatedNtf.getTargetType().equalsIgnoreCase("event")) // this is for event
		{
			  Event event = eventService.getEventById(updatedNtf.getTargetId());
			  //event.setApprovalComments(updatedNtf.getAction());
			  //event.setApprovalStatus(updatedNtf.getAction()); 
			  if(updatedNtf.getAction().equalsIgnoreCase("Approved"))
				  event.setStatus(2);
			  else if(updatedNtf.getAction().equalsIgnoreCase("Rejected"))
				  event.setStatus(4);
			  event.setUpdatedTime(new Date()); 
			  event.setUpdatedBy(updatedNtf.getUpdatedBy());
			  if(event.getUpdatedBy()!=null) {
				  //if event has been processed before
				  //that means this request is for update 
				  //in which case notificaion should be sent to those who have joined the event
				  //Change this according to the logic in update for events
				  //NTF rsvp(change uodated by to event_registered
				  Notification newNtf = new Notification();
				  newNtf.setBroadcastType("multiple");
				  newNtf.setCreatedBy("SYSTEM");
				  newNtf.setCreatedTime(new Date());
				  newNtf.setMappingTableData("event_user");
				  newNtf.setMessage(updatedNtf.getMessage());
				  newNtf.setTargetId(updatedNtf.getTargetId()); //user id of current user, change it to event_id
				  newNtf.setTargetType("event");
				  newNtf.setTitle(event.getEventTitle()+" Updates");
				  newNtf.setUuid( UUID.randomUUID());
				  ntfs.saveNotification(newNtf);
			  }
			  event.setIsProcessed(true);
			  //System.out.println("After " +event.toString());
			  eventService.processEvent(event);
		}
		else if(updatedNtf.getTargetType().equalsIgnoreCase("team")) // this is for user // this needs to be changed and adjusted for event and user as well
		{		
			  Team team = teamService.getTeamById(updatedNtf.getTargetId());
			  team.setApprovalComments(updatedNtf.getAction());
			  team.setApprovalStatus(updatedNtf.getAction()); 
			  team.setIsProcessed(true);
			  team.setUpdatedBy(updatedNtf.getUpdatedBy()); //changed to get the email?
			  team.setUpdatedTime(updatedNtf.getUpdatedTime());
//			  if(updatedNtf.getAction().equalsIgnoreCase("Approved"))
//				 {
//				  userService.getUserByEmailId(team.getTeamLeadId()).get().setRoleId(4);
//				  }
			  if(!team.getIsProcessed()) {
				  //if team has been processed before
				  //that means this request is for update 
				  //in which case notificaion should be sent to those who have joined the event
				  //Chnage the logic according to change in the update logic for teams
				  Notification newNtf = new Notification();
				  newNtf.setBroadcastType("multiple");
				  newNtf.setCreatedBy("SYSTEM");
				  newNtf.setCreatedTime(new Date());
				  newNtf.setMappingTableData("team_user");
				  newNtf.setMessage(updatedNtf.getMessage());
				  newNtf.setTargetId(updatedNtf.getTargetId());
				  newNtf.setTargetType("team");
				  newNtf.setTitle(team.getTeamTitle()+" Updates");
				  newNtf.setUuid( UUID.randomUUID());
				  ntfs.saveNotification(newNtf);
			  }
				  team.setIsProcessed(true);
			  teamService.updateTeam(team);
			}
		else if (updatedNtf.getTargetType().equalsIgnoreCase("user")) 
		{
			  User userTarget = userService.getUserById(updatedNtf.getTargetId());
			  userTarget.setApprovalComments(updatedNtf.getAction());
			  userTarget.setApprovalStatus(updatedNtf.getAction()); 
			  userTarget.setIsProcessed(true);
			  userTarget.setUpdatedBy("System");
			  userTarget.setUpdatedTime(new Date());
			  userService.updateUser(userTarget);
		}

		return resp;
	}
	
	/**
	 * To get the notifications based on the specs
	 * 
	  */
	
	@PutMapping("/getntf")
	public List<NotificationUi> getNtf(@RequestHeader("Authorization") String authHead, @RequestBody String createdDate) throws HttpException {
		List<NotificationUi> req = ntfs.getntf(getJwt(authHead), createdDate);
		return req;
	}
	
	//Deletes ntf from notification_approval table
	@PutMapping(path = "/deletenotificationapproval")
	public void deleteNotificationAppr(@RequestBody NotificationApproval newNtf) {
		System.out.println(newNtf);
		ntfs.deleteNotificationAppr(newNtf);
	}
	
	//Deletes ntf from notification table
	@PutMapping(path = "/deletenotification")
	public void deleteNotification(@RequestBody Notification newNtf) {
		System.out.println(newNtf);
		ntfs.deleteNotification(newNtf);
	}

}

