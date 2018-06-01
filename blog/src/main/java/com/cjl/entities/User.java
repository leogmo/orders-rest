package com.cjl.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.cjl.intf.Validable;
import com.google.gson.annotations.Expose;

@Entity
@Table(name="users")
public class User implements Validable{
	@Id
	@GeneratedValue
	@Expose
	private Long userId;
	@Column(name="name")
	@Expose
	private String name;
	@Column(name="email")
	@Expose
	private String email;
	@Column(name="password")
	@Expose
	private String password;
	@Column(name="token")
	@Expose
	private String token;
	
	public User() {}
	
	public User(Long userId, String name, String email, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	@Override
	public boolean isValid() {
		return userId != null && 
			  (name != null && !name.isEmpty()) &&
			  (email != null && !email.isEmpty()) &&
			  (password != null && !password.isEmpty());
	}

	public Long getUserId() {
		return userId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}
	
	public static User getInvalidUser() {
		return new User(-1L, "Invalid User", "", "");
	}

	public String getToken() {
		return token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
}
