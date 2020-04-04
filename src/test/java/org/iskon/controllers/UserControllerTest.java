package org.iskon.controllers;

import java.util.List;

import org.junit.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import junit.framework.TestCase;

public class UserControllerTest extends TestCase {

	@Test(timeout=3000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();

		ResponseEntity<List<String>> ridesResponse = restTemplate.exchange(
				"http://localhost:8080/users", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<String>>() {
				});
		List<String> users = ridesResponse.getBody();

		for (String user : users) {
			System.out.println("User: " + user);
		}
	}
}
