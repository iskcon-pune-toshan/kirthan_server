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

import org.iskon.models.NotificationModel;
import org.iskon.services.NotificationService;
import org.iskon.utils.HttpException;


@RestController
@RequestMapping("/notifications")
public class NotificationController {

	@Autowired
	private NotificationService ntfs;
	
	@SuppressWarnings("unused")
	@GetMapping()
	public ResponseEntity<Map<String,Object>> fetchNotificaionById(@RequestBody Map<String,Object> User) {
		/**
		 * User requires to store the minimum amount of information needed to authorise it
		 */
		Map<String,Object> response = new HashMap<>();
		HttpStatus respCode;
		try {
			String userId = (String) User.get("userId");
			String userToken = (String) User.get("userToken");
			if(userId == null || userId == "") {
				throw new Exception("Invalid User Credentials");
			}
			//userId will be authenticated before here
			List<NotificationModel> result = 
						ntfs.getAll(Integer.parseInt(userId));
			response.put("message",result);
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

	@PostMapping
	ResponseEntity<Map<String, Object>> saveNotification(
			@RequestBody Map<String, Object> body) {
		/**
		 * The request should come from an authenticated user
		 * With the minimum details needed for authentication(not implemented)
		 * the body should also include the details needed to send a notification
		 */
		Map<String, Object> response = new HashMap<>();
		HttpStatus respCode = HttpStatus.OK;

		try {
			NotificationModel data = new NotificationModel(body);
			String userId = (String) body.get("userId"); // createdBy
			String targetId = body.get("targetId").toString(); 
			// people to send notification to
			if (userId == null) {
				throw new HttpException("PLEASE LOGIN FIRST",
						HttpStatus.FORBIDDEN);
			}
			if (targetId == null) {
				throw new HttpException("Incorrect Target id",
						HttpStatus.FORBIDDEN);
			}
			Boolean result = ntfs.save(data, body);//response stores the meta data needs to process the data.
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
	
	@PutMapping
	ResponseEntity<Map<String, Object>> updateNofifications(
			@RequestBody Map<String, Object> body) {
		Map<String, Object> response = new HashMap<>();
		HttpStatus respCode = HttpStatus.METHOD_NOT_ALLOWED;
		response.put("message", "Oops!Method Not allowed");
		return new ResponseEntity<>(response, respCode);
	}
	
	@DeleteMapping
	ResponseEntity<String> deleteNotification() {
		return new ResponseEntity<>("Not Allowed", HttpStatus.FORBIDDEN);
	}
	
	
	@GetMapping(path="/{ntfId}")
	public ResponseEntity<Map<String,Object>> getNtfById(@RequestBody Map<String,Object> body,@PathVariable("ntfId") String ntfId) {		
		Map<String, Object> response = new HashMap<>();
		HttpStatus respCode;		
		try {
			String userId = (String) body.get("userId"); // this is to authorise													// the user 
			if (userId == null || ntfId == null)
				throw new Exception("Invalid Credentials");
			NotificationModel result= ntfs.getOne(ntfId,Integer.parseInt(userId));
			response.put("message", result);
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
	
	@PostMapping(path="/{ntfId}")
	public ResponseEntity<Map<String,Object>> saveNotificationById(){
		Map<String,Object> resp = new HashMap<>();
		resp.put("message","Method Not Allowed");
		return new ResponseEntity<Map<String,Object>>(resp, HttpStatus.FORBIDDEN);
	}
	
	//Will need to be fine tuned when working on the client and wiring
	@PutMapping(path="/{ntfId}")
	ResponseEntity<Map<String,Object>> updateNotificationById(
			@RequestBody Map<String,Object> body){
		Map<String,Object> response = new HashMap<>();
		HttpStatus respCode ;
		NotificationModel ntf = new NotificationModel(UUID.fromString((String) body.get("ntfid")),body);
		Boolean resp = ntfs.update(ntf,body);
		if(resp)respCode = HttpStatus.OK;
		else respCode = HttpStatus.BAD_REQUEST;			
		return new ResponseEntity<>(response,respCode);
	}
	
	//Ask What needs to be done here
	@DeleteMapping(path="/{ntfId}")
	public String deleteNotificationById(){
		//Depends on the app-policy
		//to be implemented
		return "Not implemented";
	}
		
}
