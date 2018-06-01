package com.cjl.test;

import java.text.DateFormat;
import java.util.Date;

import com.cjl.entities.Order;
import com.cjl.entities.Product;
import com.cjl.entities.User;
import com.cjl.entities.Order.OrderStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Testes {
	public static void main(String[] args) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setDateFormat(DateFormat.DATE_FIELD).create();
		User user = new User(1L, "Leonardo", "leogmo@gmail.com", "109148");
		Product p1 = new Product(1L, "Product A", 10.0);
		Product p2 = new Product(2L, "Product B", 10.0);

		Order order = new Order(user, new Date());
		order.addOrderItem(p1, 1.0, 10.0);
		order.addOrderItem(p2, 2.0, 10.0);
		order.setStatus(OrderStatus.PROCESSING);

		String json = gson.toJson(order);
		System.out.println(json);
	}
}
