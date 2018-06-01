package com.cjl;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

import com.cjl.entities.User;
import com.cjl.exceptions.InvalidUserException;
import com.cjl.model.IModel;

public class LoginHandler{
	private IModel model;

    public LoginHandler(IModel model) {
        this.model = model;
    }
    
    protected String generateToken(User user) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		String aux = user.getUserId().toString() + "|" + user.getEmail() + "|" + sdf.format(new Date());
		return toBase64(aux);
	}
	
	protected String toBase64(String s) {
		return Base64.getEncoder().encodeToString(s.getBytes());
	}

    public Answer process(Map<String, String> urlParams) {
        String token = null;
        try {
        	User user = model.login(urlParams.get("email"), urlParams.get("password"));
        	if (user.isValid()) {
        		token = generateToken(user);
        		model.updateUserToken(user, token);
        		return new Answer(200, token);
        	} else {
        		return new Answer(401);
        	}
        } catch (InvalidUserException iue) {
        	return new Answer(401);
        }
    }
}
