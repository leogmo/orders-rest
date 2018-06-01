package com.cjl.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.cjl.intf.Validable;
import com.google.gson.annotations.Expose;

@Entity
@Table(name="orders")
public class Order implements Validable{
	public enum OrderStatus { 
		CANCELLED(1), DELIVERED(2), IN_TRANSIT(3), PAYMENT_DUE(4), PROCESSING(5), RETURNED(6), UNKNOWN(7);
		private final int value;
		
		OrderStatus(int value){
	        this.value = value;
	    }
	    public int getValue(){
	        return value;
	    }
	};
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Expose
	private Long orderId;
	@ManyToOne()
	@JoinColumn(name = "userId")
	@Expose
	private User customer;
	@Column(name="orderDate")
	@Temporal(TemporalType.TIMESTAMP)
	@Expose
	private Date orderDate;
	@Column(name="status")
	@Expose
	private OrderStatus status;
	
	public Order() {
		this.orderId = null;
	}
	
	public Order(User customer, Date orderDate) {
		this.customer = customer;
		this.orderDate = orderDate;
	}
	
	@OneToMany(cascade =  { CascadeType.ALL, CascadeType.MERGE, CascadeType.PERSIST }, fetch=FetchType.EAGER)
	@JoinColumn(name = "orderId")
	@Expose
	private List<OrderItem> items = new ArrayList<OrderItem>();
	
	public void addOrderItem(Product product, Double quantity, Double price) {
		items.add(new OrderItem(null, this, product, quantity, price));
	}	
	
	public void setOrderIdIntoItems() {
		items.forEach(i -> i.setOrder(this));
	}
	
	public Long getOrderId() {
		return orderId;
	}


	public User getCustomer() {
		return customer;
	}


	public Date getOrderDate() {
		return orderDate;
	}

	public Double getAmount() {
		return items.stream().mapToDouble(i -> i.getAmount()).sum();
	}
	
	public void setStatus(OrderStatus status) {
		this.status = status;
	}
	
	public OrderStatus getStatus() {
		return status;
	}
	
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}

	@Override
	public boolean isValid() {
		boolean hasInvalidItems = !items.stream().filter(i -> !i.isValid()).collect(Collectors.toList()).isEmpty();
		return !items.isEmpty() && (orderDate != null) && (customer != null && customer.isValid()) && !hasInvalidItems;
	}
}
