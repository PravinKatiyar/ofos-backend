package com.capgemini.onlinefoodordersys.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_order")
public class Userorder implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8736554796212043430L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "orderId")
	private long orderId;

	private String orderDateTime;

	private double totalCost;

	private String orderStatus;

	private int quantity;

	@ManyToOne()
	@JoinColumn(name = "userId", nullable = false)
	@JsonIgnore
	private User user;

	@ManyToOne
	@JoinColumn(name = "foodItemId", nullable = false)
	//@JsonIgnore
	private FoodItems foodItems;
	
	public String getItemName() {
		return foodItems.getItemName();
	}
	
	public String getRestaurantName() {
		return foodItems.getRestaurant().getRestaurantName();
	}
	
	public String getPhoneNumber() {
		return foodItems.getRestaurant().getPhoneNumber();
	}
	
	
	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public FoodItems getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(FoodItems foodItems) {
		this.foodItems = foodItems;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	private String userAddress;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(String orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user= user;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}



}