package com.cjl.model.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.cjl.entities.Order;
import com.cjl.entities.Order.OrderStatus;
import com.cjl.entities.Product;
import com.cjl.entities.User;
import com.cjl.exceptions.InvalidUserException;
import com.cjl.model.IModel;

public class DBModel implements IModel{
	
	private static IModel instance = null;
	
	private DBModel() {
	}

	public static IModel getInstance() {
		if (instance == null) {
			instance = new DBModel();
		}
		return instance;
	}
	
	@Override
	public User login(String email, String password) throws InvalidUserException {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		User user = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("Select u from User u where u.email = :email");
			session.beginTransaction();
			Query query = session.createQuery(sql.toString());
			query.setString("email", email);

			user = (User)query.uniqueResult();
			
			if (!user.getPassword().equals(password)) {
				user = User.getInvalidUser();
			}
			session.getTransaction().commit();
	       } catch (Exception e) {
	           e.printStackTrace();
	           user = User.getInvalidUser();
	           session.getTransaction().rollback();
	       } 
		return user;
	}

	@Override
	public void updateUserToken(User user, String token) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			user.setToken(token);
			session.saveOrUpdate(user);
			session.getTransaction().commit();
	 	  
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
	}

	@Override
	public List<Product> getProducts() {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		List<Product> products = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("Select p from Product p order by p.name");
			session.beginTransaction();
			Query query = session.createQuery(sql.toString());

			products = query.list();
			
			session.getTransaction().commit();
	       } catch (Exception e) {
	           e.printStackTrace();
	           products = new ArrayList<Product>();
	           session.getTransaction().rollback();
	       } 
		return products;
	}

	@Override
	public Order placeOrder(Order order) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		order.setOrderIdIntoItems();
		try {
			session.beginTransaction();
			session.saveOrUpdate(order);
			session.getTransaction().commit();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
       }
 	   return order;
	}

	@Override
	public boolean cancelOrder(Order order) {
		order.setStatus(OrderStatus.CANCELLED);
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(order);
			session.getTransaction().commit();
			return true;
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
           return false;
       }
	}

	@Override
	public OrderStatus getOrderStatus(Long orderId) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			Order order = (Order)session.get(Order.class, orderId);
			session.getTransaction().commit();
			return order.getStatus();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
           return OrderStatus.UNKNOWN;
       }
	}

	@Override
	public String getUserToken(Long userId) {
		SessionFactory factory = HibernateUtil.getSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			User user = (User)session.get(User.class, userId);
			session.getTransaction().commit();
			return user.getToken();
       } catch (Exception e) {
           e.printStackTrace();
           session.getTransaction().rollback();
           return "";
       }
	}

}
