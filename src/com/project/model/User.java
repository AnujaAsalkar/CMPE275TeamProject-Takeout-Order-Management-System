package com.project.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	private String first_name;
	private String last_name;
	@Column(nullable=false)
	private String email_id;
	@Column(nullable=false)
	private String password;
	private String phone_number;
	private Integer verification_code;
	private boolean enabled; //this enabled field will be set to false. During the account verification process -if successful it will be set to true
	
	//@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	//private Set<Order_details> order_details = new HashSet<Order_details>(0);//0 means the initial capacity

	public User()
	{
		
	}
	
	public User(int user_id, String first_name, String last_name, String email_id, String password, String phone_number,
			Integer verification_code, boolean enabled, Set<Order_details> order_details) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email_id = email_id;
		this.password = password;
		this.phone_number = phone_number;
		this.verification_code = verification_code;
		this.enabled = enabled;
		//this.order_details = order_details;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	public Integer getVerification_code() {
		return verification_code;
	}

	public void setVerification_code(Integer verification_code) {
		this.verification_code = verification_code;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

/*	public Set<Order_details> getOrder_details() {
		return order_details;
	}

	public void setOrder_details(Set<Order_details> order_details) {
		this.order_details = order_details;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", email_id="
				+ email_id + ", password=" + password + ", phone_number=" + phone_number + ", verification_code="
				+ verification_code + ", enabled=" + enabled + ", order_details=" + order_details + "]";
	}
	*/
	
	
	
	

}
