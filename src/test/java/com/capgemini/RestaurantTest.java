//package com.capgemini;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import com.capgemini.onlinefoodordersys.dao.RestaurantRepository;
//import com.capgemini.onlinefoodordersys.model.Restaurant;
//import com.capgemini.onlinefoodordersys.service.AdminService;
//
//
//@SpringBootTest
//class RestaurantManagementApplicationTests {
//
//	@Autowired
//	private AdminService service;
//	
//	@MockBean
//	private RestaurantRepository resdao;
//	@Test
//	public void viewAllRestaurantsTest() {
//		when(resdao.findAll()).thenReturn(Stream.of(new Restaurant(1,"Avinash","Avinash","9948820429","avinash@gmail.com","Hyderabad","hyderabad","1234")).collect(Collectors.toList()));
//     assertEquals(1,service.fetchAllRestaurants().size());
//	}
//	
//	@Test
//	public void createRestaurantTest() {
//		Restaurant restaurant=new Restaurant(22,"Avinash","dabha","9948820429","avinash@gmail.com","Hyderabad","hyderabad","1234");
//		when(resdao.save(restaurant)).thenReturn(restaurant);
//		assertEquals(restaurant,service.createRestaurant(restaurant)); 
//	}
//	
//	@Test
//	public void deleteRestaurantTest() {
//		Restaurant restaurant=new Restaurant(27,"Avinash","dabha","9948820429","avinash@gmail.com","Hyderabad","hyderabad","1234");
//		int restaurantId=27;
//		service.deleteRestaurant(restaurantId);
//		verify(service,times(1)).deleteRestaurant(restaurantId);
//	}
//	@Test
//	
//	public void updateRestaurantTest() {
//		Restaurant restaurant=new Restaurant(26,"Avinash","dabha","9948820429","avinash@gmail.com","Hyderabad","hyderabad","1234");
//		
//		when(resdao.save(restaurant)).thenReturn(restaurant);
//		assertEquals(restaurant,service.updateRestaurant(restaurant)); 
//		
//	}
//	@Test
//	void contextLoads() {
//	}
//
//}
