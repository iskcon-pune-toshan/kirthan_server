package org.iskon.utils;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Component
public class FireBaseAdminConfig {
	
	@PostConstruct
	public void Config() throws IOException{
		System.out.println("Admin config called");	
		String fireBaseConfigPath = "iskconkirthan-firebase-adminsdk-17jep-75f5190c72.json";
		FirebaseOptions options = new FirebaseOptions.Builder()
										.setCredentials(
												GoogleCredentials
												.fromStream(new ClassPathResource(fireBaseConfigPath)
														.getInputStream()))
										.build();
		if(FirebaseApp.getApps().isEmpty()) {
			FirebaseApp.initializeApp(options);
		}
	}
}