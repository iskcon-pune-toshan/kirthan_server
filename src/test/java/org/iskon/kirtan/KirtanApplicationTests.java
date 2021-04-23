package org.iskon.kirtan;

import org.iskon.models.InviteCode;
import org.iskon.repositories.InviteCodeJpaRepository;
import org.iskon.services.EmailService;
import org.iskon.services.InviteCodeService;
import org.iskon.utils.OTPGenerator;
import org.junit.internal.runners.statements.Fail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@SpringBootTest
class KirtanApplicationTests {

	@Autowired
	private EmailService emailService;

	@Autowired
	private InviteCodeService inviteCodeService;

	@Autowired
	private InviteCodeJpaRepository inviteCodeJpaRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void sendSampleEmailMessage() {
		//emailService.sendSimpleMessage("testemail@gmail.com","test mail invite code", "Please ignore this is test mail");
	}

	@Test
	void testOTPGeneration() {
		System.out.println("Generated OTP: " + OTPGenerator.random(6));
	}

	@Test
	void testSimpleOTPGeneration() {
		System.out.println("Generated Simple OTP: " + OTPGenerator.simple());
	}

	@Test
	void testInviteCodeFlow() throws Exception {
		/*String email = "phani.karthik@gmail.com";
		inviteCodeService.inviteUserByCode(email, "admin@volstory.com");
		Optional<InviteCode> optionalInviteCode = inviteCodeJpaRepository.findById(email);
		String code = "";
		if(optionalInviteCode.isPresent()) {
			InviteCode inviteCode = optionalInviteCode.get();
			Assertions.assertTrue(inviteCodeService.isInviteCodeRedeemable(email, inviteCode.getCode()));
			code = inviteCode.getCode();
		} else {
			Assertions.fail("invite code not present in db");
		}
		inviteCodeService.redeemInviteCode(email, code);*/
	}
}
