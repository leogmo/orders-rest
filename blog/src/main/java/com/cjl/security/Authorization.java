package com.cjl.security;

import java.util.Base64;

import com.cjl.model.IModel;

public class Authorization {
	private static final String AUTHENTICATION_SCHEME = "Bearer ";
	private IModel model;
	
	public Authorization(IModel model) {
		this.model = model;
	}
	
	public boolean isAuthorized(String token) {
		String tk = token.replace(AUTHENTICATION_SCHEME, "");
		String tokenDecoded = new String(Base64.getDecoder().decode(tk.getBytes()));
		Long userId = Long.valueOf(tokenDecoded.split("|")[0]);
		return tk.equals(model.getUserToken(userId));
	}
}
