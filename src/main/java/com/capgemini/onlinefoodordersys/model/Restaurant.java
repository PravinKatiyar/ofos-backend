package com.capgemini.onlinefoodordersys.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int restaurantId;
	private String restaurantName;
	private String ownerName;
	private String phoneNumber;
	private String email;
	private String address;

	@OneToMany(mappedBy = "restaurant",cascade = CascadeType.ALL)
	private List<FoodItems> fooditems = new ArrayList<>();

	public Restaurant() {
		super();
	}

	public Restaurant(int i, String string, String string2, String string3, String string4, String string5,
			String string6, String string7) {
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<FoodItems> getFooditems() {
		return fooditems;
	}

	public void setFooditems(List<FoodItems> fooditems) {
		this.fooditems = fooditems;
	}

}
