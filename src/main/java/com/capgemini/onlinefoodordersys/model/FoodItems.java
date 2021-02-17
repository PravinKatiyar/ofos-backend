package com.capgemini.onlinefoodordersys.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class FoodItems {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int itemId;
	private String itemName;
	private double itemPrice;
	private int itemQuantity;
	private String itemAvailability;
	private String itemType;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurantId", nullable = false)
	@JsonIgnore
	private Restaurant restaurant;
	
	public FoodItems(int itemId, String itemName, double itemPrice, int itemQuantity, String itemAvailability,
			String itemType, Restaurant restaurant) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemQuantity = itemQuantity;
		this.itemAvailability = itemAvailability;
		this.itemType = itemType;
		this.restaurant = restaurant;
	}
	
	public String getRestaurantName() {
		return restaurant.getRestaurantName();
	}
	
	public int getRestaurantId() {
		return restaurant.getRestaurantId();
	}
	
	public FoodItems() {
		super();
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(double itemPrice) {
		this.itemPrice = itemPrice;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemAvailability() {
		return itemAvailability;
	}

	public void setItemAvailability(String itemAvailability) {
		this.itemAvailability = itemAvailability;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	@JsonIgnore
	public Restaurant getRestaurant() {
		return restaurant;
	}
	@JsonIgnore
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}



}
