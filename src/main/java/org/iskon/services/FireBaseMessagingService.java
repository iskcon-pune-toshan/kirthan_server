package org.iskon.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.AndroidNotification;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

@Service
public class FireBaseMessagingService {
	private String channelId = "Kirtan@ISKON"; // replace this with channel that
												// we create for the
	public String sendToUser(Map<String,String> ntf,String tokenList) {

		Message message = Message.builder()
				.setNotification(com.google.firebase.messaging.Notification.builder().setTitle(ntf.get("title"))
						.setBody(ntf.get("message")).build())
				.setAndroidConfig(AndroidConfig.builder()
						.setNotification(AndroidNotification.builder()
								.setIcon("flutter")
								.setColor("#f45342").setChannelId(channelId)
								.build())
						.build())
				.putData("click_action", "FLUTTER_NOTIFICATION_CLICK")
				.setToken(tokenList).build();
		String response;
		try {
			response = FirebaseMessaging.getInstance().send(message);
			System.out.println("Successfully sent the notification");
		}
		// Response is a message ID string.
		catch (Exception err) {
			System.out.println(err.getMessage());
			response = null;
		}
		return response;
	}
}
