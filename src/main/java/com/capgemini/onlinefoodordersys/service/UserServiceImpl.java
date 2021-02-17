package com.capgemini.onlinefoodordersys.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.onlinefoodordersys.dao.DeliveryGuyRepository;
import com.capgemini.onlinefoodordersys.dao.FoodItemsRepository;
import com.capgemini.onlinefoodordersys.dao.OrderRepository;
import com.capgemini.onlinefoodordersys.dao.TrackOrderRepository;
import com.capgemini.onlinefoodordersys.dao.UserRepository;
import com.capgemini.onlinefoodordersys.exception.IdNotFoundException;
import com.capgemini.onlinefoodordersys.exception.InsufficientBalanceexception;
import com.capgemini.onlinefoodordersys.exception.InvalidItemQuantityException;
import com.capgemini.onlinefoodordersys.exception.OrderCancelException;
import com.capgemini.onlinefoodordersys.exception.UserDoesNotExistException;
import com.capgemini.onlinefoodordersys.model.FoodItems;
import com.capgemini.onlinefoodordersys.model.Trackorder;
import com.capgemini.onlinefoodordersys.model.User;
import com.capgemini.onlinefoodordersys.model.Userorder;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private FoodItemsRepository foodItemsRepository;
	
	@Autowired
	private DeliveryGuyRepository deliveryGuyRepository;
	
	@Autowired
	private TrackOrderRepository trackOrderRepository;

	@Override
	public List<User> findAll() {
		List<User> usersList = userRepository.findAll();
		return usersList;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmailIgnoreCase(email);
		return user;
	}

	@Override
	public boolean creditWallet(Long userId, int addedbalance) {
		if (userRepository.existsById(userId) == true) {
			User user1 = userRepository.findUserById(userId);
			System.out.println("Credit Method Called!!!");
			user1.setWalletBalance(user1.getWalletBalance() + addedbalance);
			System.out.println(user1.getWalletBalance());
			userRepository.save(user1);
			
			return true;
		} else {
			throw new UserDoesNotExistException("User does not exists");
		}
	}

	@Override
	public boolean debitWallet(Long userId, int orderprice) throws InsufficientBalanceexception{
		if (userRepository.existsById(userId) == true) {
			User user1 = userRepository.findUserById(userId);
			if(user1.getWalletBalance()<orderprice) {
				throw new InsufficientBalanceexception("Insufficient Wallet Balance!!, Your Balance is"+user1.getWalletBalance());
			}
			user1.setWalletBalance(user1.getWalletBalance() - orderprice);
			userRepository.save(user1);
			return true;
		} else {
			throw new UserDoesNotExistException("User does not exists");
		}
	}

	public List<Userorder> getAllOrdersByCustomerId(long userId) {
		long user = userRepository.findUserById(userId).getId();
		List<Userorder> orders = orderRepository.findByUserId(user);
		return orders;
	}

	@Override
	public Userorder addOrder(int itemQuantity, Long customerId, int fooditemId)  throws InsufficientBalanceexception {

		return userRepository.findById(customerId).map(user -> {
			Userorder userorder = new Userorder();
			int itemquantityfromdb=foodItemsRepository.findByItemId(fooditemId).getItemQuantity();
			if(itemQuantity>itemquantityfromdb) {
				throw new InvalidItemQuantityException("Requested Quantity is not available at the moment");
			}
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			userorder.setOrderDateTime(dtf.format(now));
			userorder.setUser(user);
			float price = foodItemsRepository.findItemsPrice(fooditemId);
			int quantity =itemQuantity;
			userorder.setQuantity(quantity);
			float totalCost= price*quantity;
			userorder.setTotalCost(totalCost);
			debitWallet(customerId, (int) userorder.getTotalCost());
			userorder.setOrderStatus("Requested");
			FoodItems temptest = foodItemsRepository.findById(fooditemId)
					.orElseThrow(() -> new IdNotFoundException("Fooditem not found!"));
			int availableQuantity=temptest.getItemQuantity();
			int itemquantity=itemQuantity;
			temptest.setItemQuantity(availableQuantity-itemquantity);
			
			userorder.setFoodItems(temptest);			
			userorder.setUserAddress(user.getAddress());

			return orderRepository.save(userorder);
		}).orElseThrow(() -> new IdNotFoundException("Restaurant not found!"));
	}

	@Override
	public void cancelOrder(long orderId) {
		Userorder order = orderRepository.getOne(orderId);
		order.getUser().getId();
		order.getTotalCost();
		
		String orderstatus= order.getOrderStatus();
		
		if(orderstatus.equalsIgnoreCase("Order Cancelled")) {
			throw new OrderCancelException("Your order is already cancelled!!");
		}
		
		creditWallet(order.getUser().getId(), (int) order.getTotalCost());
		System.out.println("Method Called!!!");
		
		order.setOrderStatus("Order Cancelled");
		orderRepository.save(order);

	}

	@Override
	public List<FoodItems> findFoodItemsbyRestaurant(int restaurantId) {
		return foodItemsRepository.findItemsByRestaurant(restaurantId);
	}

	@Override
	public List<FoodItems> findAllAvailableFoodItems() {
		return foodItemsRepository.findAllActiveFoodItems();
	}

	@Override
	public Trackorder trackOrder(long orderId) {
		
	Trackorder	trackOrder = new Trackorder();
	
	long id = orderRepository.getOne(orderId).getOrderId();
	
	trackOrder.setTrackOrderId(id);
	trackOrder.setStatus("Out For Delivery");
	trackOrder.setDeliveryAddress(orderRepository.findAddress(id));
	trackOrder.setDeliveryGuy(null);
	
	return trackOrderRepository.save(trackOrder);	
		
		
	}

	@Override
	public List<FoodItems> fetchFoodItemByRestaurantavailablefoodItems(int restaurantId) {
		return foodItemsRepository.fetchFoodItemByRestaurantavailablefoodItems(restaurantId);
	}

	@Override
	public Userorder viewOrderById(Long orderId) {
		return orderRepository.findorderByorderId(orderId);
	}
	
	

}
