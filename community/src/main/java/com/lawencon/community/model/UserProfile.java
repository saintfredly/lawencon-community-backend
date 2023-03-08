package com.lawencon.community.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.lawencon.base.BaseEntity;

@Entity
@Table(name = "t_user_profile",
uniqueConstraints = {
        @UniqueConstraint(name = "user_profile_ck", 
                columnNames = {"user", "industry", "position"}
        )})
public class UserProfile extends BaseEntity {

	@OneToOne
	@JoinColumn(nullable = false, name = "user_id")
	private User user;
	
	@Column(nullable = false, length = 50)
	private String fullName;
	
	@Column(length = 50)
	private String company;
	
	@OneToOne
	@JoinColumn(name = "industry_id")
	private Industry industry;
	
	@OneToOne
	@JoinColumn(name = "position_id")
	private Position position;
	
	@Column(columnDefinition = "text")
	private String address;
	
	@Column(length = 15)
	private String phone;

	private float balance;
	private boolean subscribe;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Industry getIndustry() {
		return industry;
	}

	public void setIndustry(Industry industry) {
		this.industry = industry;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public boolean isSubscribe() {
		return subscribe;
	}

	public void setSubscribe(boolean subscribe) {
		this.subscribe = subscribe;
	}

}
