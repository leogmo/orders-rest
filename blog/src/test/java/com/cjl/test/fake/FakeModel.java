package com.cjl.test.fake;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.cjl.entities.Order;
import com.cjl.entities.Order.OrderStatus;
import com.cjl.entities.Product;
import com.cjl.entities.User;
import com.cjl.exceptions.InvalidUserException;
import com.cjl.model.IModel;

public class FakeModel implements IModel{
	private Map<Long, String> users = new HashMap<Long, String>();

	@Override
	public User login(String email, String password) throws InvalidUserException {
		User user = null;
		if (email.equals("test@test.com") && password.equals("12345")) {
			user = new User(1L, "foo", email, password);
		} else {
			user = User.getInvalidUser();
		}
		if (!user.isValid()) { throw new InvalidUserException(); }
		
		return user;
	}
	
	@Override
	public List<Product> getProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order placeOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean cancelOrder(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public OrderStatus getOrderStatus(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUserToken(User user, String token) {
		users.put(user.getUserId(), token);
	}

	@Override
	public String getUserToken(Long userId) {
		return users.get(userId);
	}

}
