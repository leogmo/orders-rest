package com.cjl;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

import com.cjl.entities.Order;
import com.cjl.model.IModel;
import com.cjl.model.impl.DBModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import spark.Request;
import spark.Response;
import spark.Route;

public class OrderController {
	protected static IModel model = DBModel.getInstance();
	
	public static Route placeOrder = (Request request, Response response) -> {
        String orderJs = request.body();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(DateFormat.DATE_FIELD).create();
        Order order = gson.fromJson(orderJs, Order.class);
        
        OrderHandler handler = new OrderHandler(model);
		Answer answer = handler.placeOrder(order);
		
		response.status(answer.getCode());
		response.type("text/html");
		response.body(answer.getBody());
        return answer.getBody();
    };
    
    public static Route cancelOrder = (Request request, Response response) -> {
        String orderJs = request.body();
        Gson gson = new Gson();
        Order order = gson.fromJson(orderJs, Order.class);
        
        OrderHandler handler = new OrderHandler(model);
		Answer answer = handler.cancelOrder(order);
		
		response.status(answer.getCode());
		response.type("text/html");
		response.body(answer.getBody());
        return answer.getBody();
    };
    
    public static Route orderStatus = (Request request, Response response) -> {
        String orderJs = request.body();
        Gson gson = new Gson();
        Order order = gson.fromJson(orderJs, Order.class);
        
        OrderHandler handler = new OrderHandler(model);
		Answer answer = handler.orderStatus(order);
		
		response.status(answer.getCode());
		response.type("text/html");
		response.body(answer.getBody());
        return answer.getBody();
    };
    
    public static Route getProducts = (Request request, Response response) -> {
        OrderHandler handler = new OrderHandler(model);
		Answer answer = handler.getProducts();
		
		response.status(answer.getCode());
		response.type("application/json");
		response.body(answer.getBody());
        return answer.getBody();
    };
}
