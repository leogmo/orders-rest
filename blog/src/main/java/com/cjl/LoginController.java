package com.cjl;

import java.util.HashMap;
import java.util.Map;

import com.cjl.model.IModel;
import com.cjl.model.impl.DBModel;

import spark.Request;
import spark.Response;
import spark.Route;

public class LoginController {
	protected static IModel model = DBModel.getInstance();
	
	public static Route doLogin = (Request request, Response response) -> {
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", request.queryParams("email"));
        params.put("password", request.queryParams("password"));
        
        LoginHandler handler = new LoginHandler(model);
		Answer answer = handler.process(params);
		response.status(answer.getCode());
		response.type("text/html");
		response.body(answer.getBody());
        return answer.getBody();
    };
}
