package com.cjl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.cjl.Answer;
import com.cjl.EmptyPayload;
import com.cjl.LoginHandler;
import com.cjl.exceptions.InvalidUserException;
import com.cjl.model.IModel;
import com.cjl.test.fake.FakeModel;

public class LoginTest {
	IModel model = new FakeModel();
	LoginHandler loginHndl = new LoginHandler(model);
	EmptyPayload emptyPayload = new EmptyPayload();
	Map<String, String> params = new HashMap<String, String>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

	@Test
	public void validUserReturnsToken() throws InvalidUserException {
		params.put("email", "test@test.com");
		params.put("password", "12345");
		String date = sdf.format(new Date());
		String token = Base64.getEncoder().encodeToString(("1|test@test.com|"+date).getBytes());
		
		Answer answer = loginHndl.process(params);
				
		assertEquals(new Answer(200, token), answer);
	}
	
	@Test
	public void invalidUserReturns401() {
		params.put("email", "test@test.com");
		params.put("password", "123456");
		Answer answer = loginHndl.process(params);
		assertEquals(new Answer(401), answer);
	}
	
	@Test
	public void validUserLoginUpdateUserToken() throws InvalidUserException {
		params.put("email", "test@test.com");
		params.put("password", "12345");
		
		Answer answer = loginHndl.process(params);
		
		String updatedToken = model.getUserToken(1L);
		
		assertEquals(answer.getBody(), updatedToken);
	}
}
