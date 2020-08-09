package org.iskon.services;

import java.util.Map;
import org.iskon.models.NotificationModel;
import org.springframework.stereotype.Service;
import com.google.firebase.messaging.*;

@Service
public class FireBaseMessagingService {
	private String channelId = "Kirtan@ISKON"; // replace this with channel that
												// we create for the
	public String sendToUser(Map<String, String> data, Map<String,Object> ntf,
			String tokenList) {

		Message message = Message.builder()
				.setNotification(Notification.builder().setTitle((String) ntf.get("title"))
						.setBody((String) ntf.get("message")).build())
				.setAndroidConfig(AndroidConfig.builder()
						.setNotification(AndroidNotification.builder()
								.setIcon("stock_ticker_update")
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
