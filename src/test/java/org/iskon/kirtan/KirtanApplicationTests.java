package org.iskon.kirtan;

import org.iskon.services.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KirtanApplicationTests {

	@Autowired
	EmailService emailService;

	@Test
	void contextLoads() {
	}

	@Test
	void sendSampleEmailMessage() {
		//emailService.sendSimpleMessage("testemail@gmail.com","test mail invite code", "Please ignore this is test mail");
	}
}
