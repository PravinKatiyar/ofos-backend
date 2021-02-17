package com.capgemini.onlinefoodordersys.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinefoodordersys.dao.DeliveryGuyRepository;
import com.capgemini.onlinefoodordersys.dao.FoodItemsRepository;
import com.capgemini.onlinefoodordersys.dao.OrderRepository;
import com.capgemini.onlinefoodordersys.dao.RestaurantRepository;
import com.capgemini.onlinefoodordersys.dao.UserRepository;
import com.capgemini.onlinefoodordersys.exception.ContactNumberAlreadyExistException;
import com.capgemini.onlinefoodordersys.exception.DeliveryGuyListNotFoundException;
import com.capgemini.onlinefoodordersys.exception.DeliveryGuyNotFoundException;
import com.capgemini.onlinefoodordersys.exception.FoodItemNotFoundException;
import com.capgemini.onlinefoodordersys.exception.OrderException;
import com.capgemini.onlinefoodordersys.exception.RestaurantNotFoundException;
import com.capgemini.onlinefoodordersys.model.DeliveryGuy;
import com.capgemini.onlinefoodordersys.model.FoodItems;
import com.capgemini.onlinefoodordersys.model.Restaurant;
import com.capgemini.onlinefoodordersys.model.User;
import com.capgemini.onlinefoodordersys.model.Userorder;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	RestaurantRepository resdao;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private DeliveryGuyRepository deliveryGuyRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private FoodItemsRepository foodItemsRepository;

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmailIgnoreCase(email);
		return user;
	}

	@Override
	public Optional<Restaurant> getRestaurantDetails(int id) {
		boolean bool = resdao.existsById(id);
		if (bool = true) {
			return resdao.findById(id);
		}
		return null;
	}

	@Override
	public Restaurant createRestaurant(Restaurant restaurant) {
		// TODO Auto-generated method stub
		return resdao.save(restaurant);
	}

	@Override
	// *************************IT IS WRONG
	// **************************************

	public Restaurant updateRestaurant(Restaurant restaurant) {
		boolean bool = resdao.existsById(restaurant.getRestaurantId());
		if (bool == true) {
			resdao.save(restaurant);
			return restaurant;
		} else {
			resdao.save(restaurant);
			return restaurant;
		}
	}

	@Override
	public void deleteRestaurant(int id) {
		resdao.deleteById(id);

	}

	@Override
	public List<Restaurant> fetchAllRestaurants() {
		return resdao.findAll();
	}

	@Override
	public DeliveryGuy adddeliveryGuy(DeliveryGuy deliveryGuy) {
		if (deliveryGuyRepository.findDeliveryGuyByGuyPhone(deliveryGuy.getGuyPhone()) != null)
			throw new ContactNumberAlreadyExistException(
					"Delivery Guy with Phone number" + deliveryGuy.getGuyPhone() + " is Already Exist");

		return deliveryGuyRepository.save(deliveryGuy);
	}

	@Override
	public void deleteDeliveryGuy(Long id) {
		if (deliveryGuyRepository.findById(id) != null) {
			deliveryGuyRepository.deleteById(id);
		} else
			throw new DeliveryGuyNotFoundException("Delivery Guy does not exists!!");

	}

	@Override
	public List<DeliveryGuy> fetchAlldeliveryGuys() {
		List<DeliveryGuy> list = deliveryGuyRepository.findAll();
		if (list.size() == 0) {
			throw new DeliveryGuyListNotFoundException("No Delivery Guy in the list!1");
		}
		return list;
	}

	@Override
	public void changeAvailabilityStatus(long id) {
		DeliveryGuy tempdeliveryGuy = deliveryGuyRepository.findDeliveryGuyByGuyId(id);
		if (tempdeliveryGuy == null)
			throw new DeliveryGuyNotFoundException("Delivery Guy with id " + id + " does not exists");
		else {
			if (tempdeliveryGuy.getAvailability().equals("Available")) {
				tempdeliveryGuy.setAvailability("Unavailabile");
			} else {
				tempdeliveryGuy.setAvailability("Available");
			}
			deliveryGuyRepository.save(tempdeliveryGuy);

		}

	}

	@Override
	public Iterable<Userorder> listAllOrder() {
		Iterable<Userorder> list = orderRepository.viewAllnonCancelledOrders();
		return list;
	}

	@Override
	public List<Userorder> viewOrderByCustomerId(long userId) {
		long user = userRepository.findUserById(userId).getId();
		List<Userorder> list = orderRepository.findByUserId(user);

		return list;
	}

	@Override
	public Userorder viewOrderById(long id) {
		Optional<Userorder> order = orderRepository.findById(id);

		return order.get();
	}

	@Override
	public void acceptUserOrder(long orderId) {
		Userorder order = orderRepository.getOne(orderId);
		
		if(order.getOrderStatus().equalsIgnoreCase("Order Cancelled")) {
			throw new OrderException("You can't Accept already cancelled order!!");
		}
		
		
		order.setOrderStatus("Accepted");
		orderRepository.save(order);

	}

	@Override
	public void rejectUserOrder(long orderId) {
		Userorder order = orderRepository.getOne(orderId);
		if(order.getOrderStatus().equalsIgnoreCase("Order Cancelled")) {
			throw new OrderException("You can't Reject already cancelled order!!");
		}
		order.setOrderStatus("Rejected");
		orderRepository.save(order);

	}
	
	@Override
	public void orderOutForDelivery(long id) {
		Userorder order = orderRepository.getOne(id);
		if(order.getOrderStatus().equalsIgnoreCase("Order Cancelled")) {
			throw new OrderException("Order is already cancelled by the user");
		}
		
		if(order.getOrderStatus().equalsIgnoreCase("Out for Delivery")) {
			throw new OrderException("Order is already gone out for Delivery!!");
		}
		order.setOrderStatus("Out for Delivery");
		orderRepository.save(order);
	}
	
	
	// get all food items
		public List<FoodItems> getFoodItems() {
			return foodItemsRepository.findAll();
		}
		
		//get food items of particular id
		public FoodItems getFoodItemsById(int itemId) {
			return foodItemsRepository.findByItemId(itemId);
		}

		// add new food items
		@Override
		public FoodItems saveFoodItems(int restaurantId, FoodItems foodItems) {
			List<FoodItems> fooditems=new ArrayList<>();
	        Restaurant restaurant1 = new Restaurant();
	        Optional<Restaurant> byId = resdao.findById(restaurantId);
	        if (!byId.isPresent()) {
	            throw new RestaurantNotFoundException("Restaurant not found!");
	        }
	        Restaurant restaurant = byId.get();   
	        foodItems.setRestaurant(restaurant);
	        FoodItems fooditems1 = foodItemsRepository.save(foodItems);
	        fooditems.add(fooditems1);
	        restaurant1.setFooditems(fooditems);
	        return fooditems1;
		}
		
		//update food items 
		@Override
		public FoodItems updateFoodItemsById(int itemId, FoodItems itemRequest) {
	        if (!foodItemsRepository.existsById(itemId)) {
	            throw new FoodItemNotFoundException("Food Item not found");
	        }
	        Optional<FoodItems> foodItems = foodItemsRepository.findById(itemId);

	        if (!foodItems.isPresent()) {
	            throw new FoodItemNotFoundException("Food Item not found");
	        }

	        FoodItems fooditems1 = foodItems.get();
	        fooditems1.setItemName(itemRequest.getItemName());
	        fooditems1.setItemPrice(itemRequest.getItemPrice());
	        fooditems1.setItemQuantity(itemRequest.getItemQuantity());
	        fooditems1.setItemAvailability(itemRequest.getItemAvailability());
	        fooditems1.setItemType(itemRequest.getItemType());

	        return foodItemsRepository.save(fooditems1);
	    }
		
		// delete food items of particular id
		public String deleteFoodItems(int itemId) {
			foodItemsRepository.deleteById(itemId);
			return "food item removed and food item Id=" + itemId;
		}

		@Override
		public void deliveredOrder(long orderId) {
			Userorder order = orderRepository.getOne(orderId);
			if(order.getOrderStatus().equalsIgnoreCase("Order Cancelled")) {
				throw new OrderException("You can't confirm delivery for already cancelled order by user!!");
				
			}else if(order.getOrderStatus().equalsIgnoreCase("Rejected")) {
				throw new OrderException("You can't confirm delivery for already Rejected order by admin!!");
				
			}else if(order.getOrderStatus().equalsIgnoreCase("Delivered")) {
				throw new OrderException("Order is already Delivered to the user!!");
				
			}else if(order.getOrderStatus().equalsIgnoreCase("Requested")) {
				throw new OrderException("Order must be accepted by admin before delivery!!");
			}
			order.setOrderStatus("Delivered");
			orderRepository.save(order);

			
		}

		

	

		
}
