package com.capgemini.onlinefoodordersys.service;

import java.util.List;

import com.capgemini.onlinefoodordersys.model.FoodItems;
import com.capgemini.onlinefoodordersys.model.Trackorder;
import com.capgemini.onlinefoodordersys.model.User;
import com.capgemini.onlinefoodordersys.model.Userorder;

public interface UserService {
	  
	List<User> findAll();
	  
	User getUserByEmail(String email);

	boolean creditWallet(Long userId, int addedbalance);

	boolean debitWallet(Long userId, int orderprice);
	
	List<Userorder> getAllOrdersByCustomerId(long userId);
	
	void cancelOrder(long userId);

	Userorder addOrder(int itemQuantity, Long customerId, int fooditemId);

	List<FoodItems> findFoodItemsbyRestaurant(int restid);

	List<FoodItems> findAllAvailableFoodItems();
	
	Trackorder trackOrder(long orderId);

	List<FoodItems> fetchFoodItemByRestaurantavailablefoodItems(int restid);

	Userorder viewOrderById(Long orderId);
	
	
	
	
	
	
	
	 


}
