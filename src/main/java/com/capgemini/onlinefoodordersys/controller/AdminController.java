package com.capgemini.onlinefoodordersys.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinefoodordersys.exception.IdNotFoundException;
import com.capgemini.onlinefoodordersys.exception.InvalidCustomerIdException;
import com.capgemini.onlinefoodordersys.model.DeliveryGuy;
import com.capgemini.onlinefoodordersys.model.FoodItems;
import com.capgemini.onlinefoodordersys.model.Restaurant;
import com.capgemini.onlinefoodordersys.model.User;
import com.capgemini.onlinefoodordersys.model.Userorder;
import com.capgemini.onlinefoodordersys.service.AdminService;
import com.capgemini.onlinefoodordersys.service.UserService;

@RestController
@CrossOrigin(value="*")
//@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService service;	

	// View List of all the users
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// View own profile
	@GetMapping(value = "/adminprofile")
	// The principal is the currently logged in user
	public ResponseEntity<User> getUser(Principal principal) {
		User user = service.getUserByEmail(principal.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// Add Delivery Boy
	@PostMapping("/addDeliveryguy")
	public ResponseEntity<String> adddeliveryGuy(@RequestBody DeliveryGuy deliveryGuy) {
		service.adddeliveryGuy(deliveryGuy);
		ResponseEntity<String> responseEntity = new ResponseEntity<String>("Delivery Guy is added successfully!!",
				HttpStatus.OK);
		return responseEntity;
	}

	// Change availability status of delivery Guy
	@PutMapping("/deliveryguy/{id}/changeAvailabilityStatus")
	public ResponseEntity<String> changeAvailabilityStatus(@PathVariable long id) {
		service.changeAvailabilityStatus(id);
		return new ResponseEntity<String>("Status is changed successfully!!", HttpStatus.OK);

	}

	// delete the delivery Guy
	@DeleteMapping("/deleteDeliveryGuy/{id}")
	public void deleteDeliveryGuy(@PathVariable Long id) {
		service.deleteDeliveryGuy(id);
	}

	// view All delivery Guys
	@GetMapping("/fetchAlldeliveryGuys")
	public List<DeliveryGuy> fetchAlldeliveryGuys() {
		List<DeliveryGuy> list = service.fetchAlldeliveryGuys();
		return list;
	}

	// changing availability status of delivery guy
	

	@GetMapping("/getRestaurantDetails/{enter restaurantId}")
	public Optional<Restaurant> getRestaurantDetails(@PathVariable("enter restaurantId") int id)
			throws IdNotFoundException {

		if (service.getRestaurantDetails(id).equals(null)) {
			throw new IdNotFoundException("Employee does not exist");
		} else {
			return service.getRestaurantDetails(id);
		}
	}

	// create the restaurant
	@PostMapping("/createRestaurant")
	public ResponseEntity<String> createEmployee(@RequestBody Restaurant restaurant) {
		Restaurant res = service.createRestaurant(restaurant);
		if (res == null) {
			throw new IdNotFoundException("Restaurant not found");
		} else {
			ResponseEntity<String> responseEntity = new ResponseEntity<String>("Restaurant is added successfully!!",
					HttpStatus.OK);
			return responseEntity;
		}
	}

	// update the restaurant

	@PutMapping("/updateRestaurant")
	public ResponseEntity<String> updateRestaurant(@RequestBody Restaurant restaurant) {
		Restaurant res = service.updateRestaurant(restaurant);
		if (res != null) {
			ResponseEntity<String> responseEntity = new ResponseEntity<String>(
					"Restaurant details were updated successfully!!" + "\nRestaurant ID : "
							+ restaurant.getRestaurantId() + "\nRestaurant Name: " + restaurant.getRestaurantName()
							+ "\nOwnerName : " + restaurant.getOwnerName() + "\nphoneNumber : "
							+ restaurant.getPhoneNumber() + "\nRestaurant email : " + restaurant.getEmail()
							+ "\n Restaurant address : " + restaurant.getAddress(),
					HttpStatus.OK);
			return responseEntity;
		} else {
			ResponseEntity<String> responseEntity = new ResponseEntity<String>(
					"Sorry, restaurant details were not updated", HttpStatus.OK);
			return responseEntity;
		}
	}

	// delete the restaurant
	@DeleteMapping("/deleteRestaurant/{id}")
	public void deleteRestaurant(@PathVariable("id") int id) {
		service.deleteRestaurant(id);
	}

	// fetch all restaurants
	@GetMapping("/fetchAllRestaurant")
	public List<Restaurant> fetchAllRestaurant() {
		List<Restaurant> list = service.fetchAllRestaurants();
		return list;
	}

	@GetMapping("/allOrders")
	public Iterable<Userorder> viewAllOrder() {
		Iterable<Userorder> orderList = service.listAllOrder();
		return orderList;
	}
	
	@GetMapping("/viewOrderByCustomerId/{customerId}")
	public List<Userorder> viewOrderByCustomerId(@PathVariable("customerId") long customerId)
			throws InvalidCustomerIdException {
		return service.viewOrderByCustomerId(customerId);
	}
	
	@GetMapping("/searchOrder/{orderId}")
	public ResponseEntity<Userorder> viewOrderById(@PathVariable("orderId") Long id) {
		Userorder order = service.viewOrderById(id);

		return new ResponseEntity<Userorder>(order, HttpStatus.OK);
	}
	
	@PutMapping("/acceptOrder/{id}")
	 public ResponseEntity<String> acceptUserOrder(@PathVariable long id) {
		 service.acceptUserOrder(id);
		return new ResponseEntity<String>("Order Accepted", HttpStatus.OK);
	}
	
	@PutMapping("/rejctedOrder/{id}")
	 public ResponseEntity<String> rejectUserOrder(@PathVariable long id) {
		 service.rejectUserOrder(id);
		return new ResponseEntity<String>("Order Rejected", HttpStatus.OK);
	}
	
	@PutMapping("/orderOutForDelivery/{id}")
	 public ResponseEntity<String> orderOutForDelivery(@PathVariable long id) {
		 service.orderOutForDelivery(id);
		return new ResponseEntity<String>("Out for Delivery", HttpStatus.OK);
	}
	@PutMapping("/deliveredOrder/{id}")
	 public ResponseEntity<String> deliveredOrder(@PathVariable long id) {
		 service.deliveredOrder(id);
		return new ResponseEntity<String>("Order Delivery", HttpStatus.OK);
	}
	
	
	
	@GetMapping("/fooditems")
	public List<FoodItems> findAllFoodItems(){
		return service.getFoodItems();
	}
	@GetMapping("/fooditems/{itemId}")
	public FoodItems findById(@PathVariable int itemId){
		return service.getFoodItemsById(itemId);
	}
	@PostMapping("/restaurantId/{restId}/addfoodItems")
	public FoodItems addFoodItems(@PathVariable Integer restId,@RequestBody FoodItems foodItems) {
		return service.saveFoodItems(restId,foodItems);
	}

	@PutMapping("/update/{itemId}")
	public FoodItems updateFoodItems(@PathVariable Integer itemId,@RequestBody FoodItems foodItems) {
		return service.updateFoodItemsById(itemId, foodItems);
	}
	
	@DeleteMapping("/delete/{itemId}")
	public String deleteFoodItems(@PathVariable int itemId) {
		return service.deleteFoodItems(itemId);
	}
	
		
}
