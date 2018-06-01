package com.cjl.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import com.cjl.entities.Order;
import com.cjl.entities.Product;
import com.cjl.entities.User;

public class OrderTest {

	@Test
	public void orderWithoutItemsIsInvalid() {
		User user = new User(1L, "Leonardo", "leogmo@gmail.com", "12345");
		
		Order order = new Order(user, new Date());
		
		assertTrue(!order.isValid());
	}
	
	@Test
	public void orderWithoutCustomerIsInvalid() {
		Order order = new Order(null, new Date());
		
		assertTrue(!order.isValid());
	}
	
	@Test
	public void orderWithInvalidCustomerIsInvalid() {
		Order order = new Order(User.getInvalidUser(), new Date());
		
		assertTrue(!order.isValid());
	}
	
	@Test
	public void itemsSumIsValid() {
		User user = new User(1L, "Leonardo", "leogmo@gmail.com", "12345");
		
		Order order = new Order(user, new Date());
		order.addOrderItem(new Product("Produto 1", 10.0), 1.0, 10.0);
		order.addOrderItem(new Product("Produto 2", 10.0), 2.0, 10.0);
		
		assertEquals(new Double(30.0), order.getAmount());
	}
	
	@Test
	public void orderWithInvalidProductIsInvalid() {
		User user = new User(1L, "Leonardo", "leogmo@gmail.com", "12345");
		
		Order order = new Order(user, new Date());
		order.addOrderItem(null, 1.0, 10.0);
		order.addOrderItem(new Product("Produto 2", 10.0), 2.0, 10.0);
		
		assertEquals(new Double(30.0), order.getAmount());
	}
	
	@Test
	public void orderWithInvalidProductPriceIsInvalid() {
		User user = new User(1L, "Leonardo", "leogmo@gmail.com", "12345");
		
		Order order = new Order(user, new Date());
		order.addOrderItem(null, 1.0, 10.0);
		order.addOrderItem(new Product("Produto 2", 10.0), 2.0, null);
		
		assertTrue(!order.isValid());
	}
	
	@Test
	public void orderWithNullProductQuantityIsInvalid() {
		User user = new User(1L, "Leonardo", "leogmo@gmail.com", "12345");
		
		Order order = new Order(user, new Date());
		order.addOrderItem(null, 1.0, 10.0);
		order.addOrderItem(new Product("Produto 2", 10.0), null, 10.0);
		
		assertTrue(!order.isValid());
	}
}
