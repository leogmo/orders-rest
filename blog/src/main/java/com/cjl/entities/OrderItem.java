package com.cjl.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.cjl.intf.Validable;
import com.google.gson.annotations.Expose;

@Entity
@Table(name="orderitems")
public class OrderItem implements Validable{
	@Id
	@GeneratedValue
	@Expose
	private Long orderItemId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId", referencedColumnName = "orderId")
	@Expose(deserialize = true, serialize = false)
	private Order order;
	@ManyToOne
	@JoinColumn(name = "productId", referencedColumnName = "productId")
	@Expose
	private Product product;
	@Column(name="quantity")
	@Expose
	private Double quantity;
	@Column(name="price")
	@Expose
	private Double price;
	
	public OrderItem() { }
	
	public OrderItem(Long orderItemId, Order order, Product product, Double quantity, Double price) {
		this.orderItemId = orderItemId;
		this.order = order;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Double getAmount() {
		
		return (price != null ? price : 0) * (quantity != null ? quantity : 0);
	}

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public boolean isValid() {
		return (product != null && product.isValid()) && price != null && quantity != null;
	}
}