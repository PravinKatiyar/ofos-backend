package com.capgemini.onlinefoodordersys.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinefoodordersys.dao.OrderRepository;
import com.capgemini.onlinefoodordersys.exception.NegativeBalanceException;
import com.capgemini.onlinefoodordersys.model.FoodItems;
import com.capgemini.onlinefoodordersys.model.User;
import com.capgemini.onlinefoodordersys.model.Userorder;
import com.capgemini.onlinefoodordersys.service.UserService;

@RestController
@CrossOrigin(value = "*")

//@PreAuthorize("hasRole('USER')")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping(value = "/userprofile")
	// View own profile
	// The principal is the currently logged in user
	public ResponseEntity<User> getUser(Principal principal) {
		User user = userService.getUserByEmail(principal.getName());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// credit wallet
	@PutMapping("/user/{userId}/walletrecharge/{addedbalance}")
	public ResponseEntity<String> addBalanceToWallet(@PathVariable Long userId, @PathVariable int addedbalance) {
		if(addedbalance<0) {
			throw new NegativeBalanceException("Its seems you are providing a negative Value");
			
		}
		boolean resp = userService.creditWallet(userId, addedbalance);
		if (resp == true) {
			return new ResponseEntity<String>("Balance is added successfully!", HttpStatus.OK);
		}
		return null;
	}

	@GetMapping("/getAllOrderOfCustomer/{userId}")
	public ResponseEntity<List<Userorder>> getAllOrderOfUser(@PathVariable Long userId) {
		List<Userorder> orders = userService.getAllOrdersByCustomerId(userId);
		return new ResponseEntity<List<Userorder>>(orders, HttpStatus.OK);
	}
	
	
    @PutMapping("/cancelOrder/{orderId}")
	public String cancelOrder(@PathVariable long orderId) {
		
		 userService.cancelOrder(orderId);
		 return "order cancelled";
	}

	@PostMapping("/addOrder/{itemQuantity}/{userId}/product/{foodItemId}")
	public Userorder addOrderm(@PathVariable int itemQuantity, @PathVariable Long userId,
			@PathVariable int foodItemId) {
		Userorder order = userService.addOrder(itemQuantity, userId, foodItemId);
		return order;
	}

	@GetMapping("/restaurant/{restid}/fooditems")
	public List<FoodItems> findFoodItemsbyRestaurant(@PathVariable int restid) {
		return userService.findFoodItemsbyRestaurant(restid);
	}
	
	@GetMapping("/restaurant/{restid}/fooditemsavailable")
	public List<FoodItems> fetchFoodItemByRestaurantavailablefoodItems(@PathVariable int restid) {
		return userService.fetchFoodItemByRestaurantavailablefoodItems(restid);
	}

	@GetMapping("/availablefooditems")
	public List<FoodItems> findAllAvailableFoodItems() {
		return userService.findAllAvailableFoodItems();
	}
	
	@GetMapping("/orderDetails/{orderId}")
	public Userorder viewOrderById(@PathVariable("orderId") Long orderId) {
		Userorder order = userService.viewOrderById(orderId);

		return order;
	}

}
