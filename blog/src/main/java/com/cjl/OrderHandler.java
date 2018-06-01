package com.cjl;

import java.util.List;

import com.cjl.entities.Order;
import com.cjl.entities.Order.OrderStatus;
import com.cjl.entities.Product;
import com.cjl.model.IModel;
import com.google.gson.Gson;

public class OrderHandler {
	private IModel model;

    public OrderHandler(IModel model) {
        this.model = model;
    }
    
    public Answer placeOrder(Order order) {
    	if (order.isValid()) {
    		model.placeOrder(order);
    		return new Answer(200, "Ok");
    	} else {
    		return new Answer(500, "Invalid Order");
    	}
    }
    
    public Answer cancelOrder(Order order) {
    	if (order.isValid()) {
    		boolean canceled = model.cancelOrder(order);
    		if (canceled) {
    			return new Answer(200, "Ok");
    		} else {
    			return new Answer(500, "Order cancellation error");
    		}
    	} else {
    		return new Answer(500, "Invalid Order");
    	}
    }
    
    public Answer orderStatus(Order order) {
    	OrderStatus status = model.getOrderStatus(order.getOrderId());
    	return new Answer(200, status.toString()); 
    }
    
    public Answer getProducts() {
    	Gson gson = new Gson();
    	List<Product> products = model.getProducts();
    	return new Answer(200, gson.toJson(products));
    }
}
