package com.cjl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cjl.intf.Validable;
import com.google.gson.annotations.Expose;

@Entity
@Table(name="products")
public class Product implements Validable{
	@Id
	@GeneratedValue
	@Expose
	private Long productId;
	@Column(name="name")
	@Expose
	private String name;
	@Column(name="price")
	@Expose
	private Double price;
	
	public Product() { }
	
	public Product(String name, Double price) {
		this.name = name;
		this.price = price;
	}

	public Product(Long productId, String name, Double price) {
		this.productId = productId;
		this.name = name;
		this.price = price;
	}

	@Override
	public boolean isValid() {
		return !name.isEmpty() && price != null;
	}

	public Long getProductId() {
		return productId;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}
}
