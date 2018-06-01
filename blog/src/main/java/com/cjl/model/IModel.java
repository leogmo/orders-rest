package com.cjl.model;

import java.util.List;
import java.util.UUID;

import com.cjl.entities.Order;
import com.cjl.entities.Order.OrderStatus;
import com.cjl.entities.Product;
import com.cjl.entities.User;
import com.cjl.exceptions.InvalidUserException;

public interface IModel {
	User login(String email, String password) throws InvalidUserException;
	void updateUserToken(User user, String token);
    List<Product> getProducts();
    Order placeOrder(Order order);
    boolean cancelOrder(Order order);
    OrderStatus getOrderStatus(Long orderId);
    String getUserToken(Long userId);
}