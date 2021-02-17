package com.capgemini.onlinefoodordersys.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.capgemini.onlinefoodordersys.model.DeliveryGuy;
import com.capgemini.onlinefoodordersys.model.FoodItems;
import com.capgemini.onlinefoodordersys.model.Restaurant;
import com.capgemini.onlinefoodordersys.model.User;
import com.capgemini.onlinefoodordersys.model.Userorder;

@Service
public interface AdminService {
	
	String restaurantName="[A-Z][a-z]{1,45}";
	String ownerName="[A-Z][a-z]{1,15}";
	String phoneNumber=("(0/91)?[7-9][0-9]{9}");
	String email="[a-zA-Z0-9+_.-]+(.+)$";
	String address="[A-Z][a-z]{1,45}";

	Optional<Restaurant> getRestaurantDetails(int id);

	Restaurant createRestaurant(Restaurant restaurant);

	Restaurant updateRestaurant(Restaurant restaurant);

	void deleteRestaurant(int id);

	List<Restaurant> fetchAllRestaurants();

	User getUserByEmail(String name);

	DeliveryGuy adddeliveryGuy(DeliveryGuy deliveryGuy);

	void deleteDeliveryGuy(Long id);

	List<DeliveryGuy> fetchAlldeliveryGuys();

	void changeAvailabilityStatus(long id);
	
	Iterable<Userorder> listAllOrder();
	
	void acceptUserOrder(long userId);
	
	void rejectUserOrder(long usedrId);
	
	 Userorder viewOrderById(long id);
	 
	 List<Userorder> viewOrderByCustomerId(long userId);

	FoodItems getFoodItemsById(int itemId);

	List<FoodItems> getFoodItems();

	String deleteFoodItems(int itemId);

	FoodItems saveFoodItems(int restaurantId, FoodItems foodItems);

	FoodItems updateFoodItemsById(int itemId, FoodItems itemRequest);

	void orderOutForDelivery(long id);

	void deliveredOrder(long id);

}
