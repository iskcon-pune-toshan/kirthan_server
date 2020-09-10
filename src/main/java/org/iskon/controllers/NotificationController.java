package org.iskon.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.iskon.models.NotificationApprovalModel;
import org.iskon.models.NotificationModel;
import org.iskon.services.NotificationService;
import org.iskon.utils.HttpException;


@RestController
@RequestMapping("/{userId}/notifications")
public class NotificationController {

	@Autowired
	private NotificationService ntfs;
	
	/**
	 * Fetches all the notifications associated with a given userId.
	 * Requires user to be an authenticated user.
	 * Implementation : User authentication needs to be added. 
	 * @param userId  String storing the unique id of the user userId. Retrieved from the url.
	 * @return response Json like object consisting of a message body that stores all the notifications in an array.
	 * @throws HttpException User-Built exception that generates an errorCode corresponding to the httpStatusCode.
	 * 
	 */
	@GetMapping()
	public ResponseEntity<Map<String,Object>> fetchNotificaion(
			
			@PathVariable("userId") String userId) throws HttpException {
		Map<String,Object> response = new HashMap<>();
		System.out.println("This was called");
		HttpStatus respCode;
		try {
			//String userToken = (String) User.get("userToken");
			if(userId == null || userId == "") {
				throw new Exception("Invalid User Credentials");
			}
			response = ntfs.getAll(Integer.parseInt(userId));
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
			@RequestBody Map<String, Object> body) throws HttpException {
		Map<String, Object> response = new HashMap<>();
		HttpStatus respCode = HttpStatus.OK;
		try {
			NotificationModel data = new NotificationModel(body);
			int userId = (int) body.get("userId"); // createdBy
			String targetId = body.get("targetId").toString(); 
			// people to send notification to
			if (userId == 0) {
				throw new HttpException("PLEASE LOGIN FIRST",
						HttpStatus.FORBIDDEN);
			}
			if (targetId == null) {
				throw new HttpException("Incorrect Target id",
						HttpStatus.FORBIDDEN);
			}
			Boolean result = ntfs.saveNotification(data, body);
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
	
	@PostMapping(path="/getApproval")
	public ResponseEntity<Map<String,Object>> saveNotificationAppr(@RequestBody Map<String,Object> body){
		Map<String, Object> response = new HashMap<>();
		System.out.println("Approval testing");
		HttpStatus respCode = HttpStatus.OK;
		try {
			NotificationApprovalModel data = new NotificationApprovalModel(body);
			String userId =  body.get("userId").toString(); // createdBy
			String targetId = body.get("targetId").toString(); 
			if (userId == null) {
				throw new HttpException("PLEASE LOGIN FIRST",
						HttpStatus.FORBIDDEN);
			}
			if (targetId == null) {
				throw new HttpException("Incorrect Target id",
						HttpStatus.FORBIDDEN);
			}
			Boolean result = ntfs.saveNotificationApproval(data, body);
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
	/** Method not allowed
	 * @return <b>HttpStatusCode</b>: 403 <b>Message</b>:Not allowed
	 */
	@PutMapping
	public ResponseEntity<Map<String, Object>> updateNofifications() {
		Map<String, Object> response = new HashMap<>();
		HttpStatus respCode = HttpStatus.METHOD_NOT_ALLOWED;
		response.put("message", "Oops!Method Not allowed");
		return new ResponseEntity<>(response, respCode);
	}
	
	/**Method not allowed
	 * @return <b>HttpStatus</b>:403
	 */
	@DeleteMapping
	public ResponseEntity<String> deleteNotification() {
		return new ResponseEntity<>(" Method Not Allowed", HttpStatus.FORBIDDEN);
	}
	
	
	/**
	 * Fetches the detailed information stored in a database about a notification given the notificationId.
	 * @param body Stores all the inforamtion received from the request body. The request Body should be a json object.It should contain all
	 * the information required for a user to be authenticated
	 * @param ntfId PathVariable thats equal to the notificationId.
	 * @return response Map consisting of the notification details if a match is found and null other wise.
	 * @throws HttpException throws exception with corresponding Http Status code and error message
	 */
	
	@GetMapping(path="/{ntfId}")
	public ResponseEntity<Map<String,Object>> fetchNtfById(
			@RequestBody Map<String,Object> body,
			@PathVariable("ntfId") String ntfId) throws HttpException {		
		Map<String, Object> response = new HashMap<>();
		HttpStatus respCode;		
		try {
			int userId = (int) body.get("userId");
			if ( userId == 0 || ntfId == null)
				throw new Exception("Invalid Credentials");
			response.put("message", ntfs.getOne(ntfId, (userId)));
			respCode = HttpStatus.OK;
		} catch (RuntimeException err) {
			response.put("message", err.getMessage());
			respCode = HttpStatus.INTERNAL_SERVER_ERROR;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.put("message", e.getMessage());
			respCode = HttpStatus.BAD_REQUEST;
		}
		return new ResponseEntity<Map<String,Object>>(response, respCode);
	}
	
	/**Method Not allowed
	 * 
	 * @return this method is not allowed
	 */
	@PostMapping(path="/{ntfId}")
	public ResponseEntity<Map<String,Object>> saveNotificationById(){
		Map<String,Object> resp = new HashMap<>();
			resp.put("message","Method Not Allowed");
		return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.FORBIDDEN);
	}
	
	/** Update the response to a approval seeking notification in the notification_approval table. 
	 * 	Current statuses used : Approved,Rejected,Wait(Waiting for response)
	 * 	body : {"userId": integer, "response",Boolean} (response: 1 = Approved, 0=Rejected).
	 * 		
	 * @param body Stores all the information from the Http request body. Should consist of the notificationId and the response for the same. 
	 * @param  ntfId NotificationId representing the uniqe string allocated to notifications.
	 * @return response returns a map with status information about the operation
	 */
	@PutMapping(path="/{ntfId}")
	public ResponseEntity<Map<String,Object>> updateNotificationById(
			@RequestBody Map<String,Object> body,
			@PathVariable("ntfId") String ntfId){
		System.out.println("Update Called");
		Map<String,Object> response = new HashMap<>();
		HttpStatus respCode ;
		body.put("ntfId",ntfId);
		Boolean resp = ntfs.updateApproval(body);
		if(resp)respCode = HttpStatus.OK;
		else respCode = HttpStatus.BAD_REQUEST;			
		return new ResponseEntity<>(response,respCode);
	}
	
	/**To be implemented<br>
	 * <b>Doubt</b> 
	 * <p>
	 * 	Whether everyone should be allowed to delete notifications belonging to them or are only admin allowed to delete notifications
	 * 	Or no one is allowed to delete notifications and notifications will be archived after a certain time period. 	
	 * </p>
	 * @return response status of the operation
	 */
	@DeleteMapping(path="/{ntfId}")
	public String deleteNotificationById(){
		//Depends on the app-policy
		//to be implemented
		return "Not implemented";
	}
		
}
