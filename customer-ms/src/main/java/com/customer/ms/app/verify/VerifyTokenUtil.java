package com.customer.ms.app.verify;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class VerifyTokenUtil {

	public boolean verifyToken(HttpServletRequest request) {
		String authToken = request.getHeader("auth_token");
		ResponseEntity<String> finalResponse = null;
		RestTemplate restTemplate = new RestTemplate();
		try {
			finalResponse = restTemplate.postForEntity("http://localhost:8000/oauth/check_token?token=" + authToken,
					null, String.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		if (finalResponse != null) {
			HttpStatus statusCode = finalResponse.getStatusCode();
			System.out.println("Toke Verified status ---- " + statusCode.value());
			if (!statusCode.equals(HttpStatus.OK))
				return false;
			return true;
		} else
			return false;
	}

}