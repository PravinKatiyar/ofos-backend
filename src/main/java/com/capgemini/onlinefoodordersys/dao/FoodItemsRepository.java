package com.capgemini.onlinefoodordersys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.onlinefoodordersys.model.FoodItems;
import com.capgemini.onlinefoodordersys.model.Userorder;

public interface FoodItemsRepository extends JpaRepository <FoodItems,Integer>{
	
	
	public FoodItems findByItemId(int itemId);
	
	@Query(value="select * from Food_items f WHERE f.item_availability = 'yes'",nativeQuery = true)
	List<FoodItems> findAllActiveFoodItems();

	@Query(value ="select * from Food_items f WHERE f.restaurant_id = ?1", nativeQuery = true)
	List<FoodItems> findItemsByRestaurant(int restaurantId);
	
	
	@Query(value= "select item_price from Food_items f WHERE f.item_id =?1", nativeQuery= true)
	float findItemsPrice(int fooditemId);

	@Query(value ="select * from Food_items f WHERE f.restaurant_id = ?1 and f.item_availability = 'Yes' and item_quantity >0", nativeQuery = true)
	public List<FoodItems> fetchFoodItemByRestaurantavailablefoodItems(int restaurantId);
	
	
	
	
}
