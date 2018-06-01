package com.cjl;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;

import com.cjl.model.impl.DBModel;
import com.cjl.security.Authorization;

public class Main {
	
	
    public static void main(String[] args) {
    	before("/protected/*", (request, response) -> {
    		String auth = request.headers("Authorization");
    		Authorization authorization = new Authorization(DBModel.getInstance());
    	    if (!authorization.isAuthorized(auth)) {
    	        halt(401, "You are not welcome here");
    	    }
    	});
    	
    	post("/auth", LoginController.doLogin);
    	
        get("/hello", (req, res) -> "Hello World");
        
        post("/protected/place-order", OrderController.placeOrder);
        
        get("/protected/products", OrderController.getProducts);
    }
}