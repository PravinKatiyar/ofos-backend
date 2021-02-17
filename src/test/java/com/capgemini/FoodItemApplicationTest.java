//package com.capgemini;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.when;
//
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.capgemini.onlinefoodordersys.dao.FoodItemsRepository;
//import com.capgemini.onlinefoodordersys.model.FoodItems;
//import com.capgemini.onlinefoodordersys.service.AdminService;
//
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//
//class FoodItemsApplicationTests {
//
//	@Autowired
//	private AdminService service;
//	
//	@MockBean
//	private FoodItemsRepository repo;
//	
//	@Test
//	public void getFoodItemsTest() {
//		when(repo.findAll()).thenReturn(Stream
//		.of(new FoodItems(1,"pepsi",100,2,"yes","soft drink", null)).collect(Collectors.toList()));
//		assertEquals(1,service.getFoodItems().size());
//	}
//
//	
//	
//}
//
