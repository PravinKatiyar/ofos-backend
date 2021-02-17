package com.capgemini;

import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.onlinefoodordersys.dao.UserRepository;
import com.capgemini.onlinefoodordersys.exception.ContactNumberAlreadyExistException;
import com.capgemini.onlinefoodordersys.exception.EmailAlreadyExistsException;
import com.capgemini.onlinefoodordersys.model.User;
import com.capgemini.onlinefoodordersys.service.AuthService;

import junit.framework.Assert;

//@SpringBootApplication
@SpringBootTest
//@ContextConfiguration
@RunWith(SpringRunner.class)
public class UserRegisterTest {
	
	@Autowired
	private  UserRepository userRepository;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private User tempuser;
	
	@Autowired
	private User tempuser2;
	
	@Rule
	public ExpectedException exp=ExpectedException.none();

	
	@Test
	 void registerUserTestValid() {
		tempuser.setFirstName("Pravin");
		tempuser.setLastName("Katiyar");
		tempuser.setEmail("pkatiyar981@gmail.com");
		tempuser.setPassword("12886301");
		tempuser.setAddress("Kanpur");
		tempuser.setContactNo("9981655670");
		tempuser.setRole("ADMIN");
		tempuser.setGender("Male");
		tempuser2=authService.registerUser(tempuser);
		Assert.assertNotNull(tempuser2);	
	}
	
	@Test	
	void registerUserTestInvalidEmail() throws RuntimeException{	
		Assertions.assertThrows(EmailAlreadyExistsException.class, () -> {		   
		tempuser.setEmail("pkatiyar981@gmail.com");
		tempuser.setFirstName("Pravin");
		tempuser.setLastName("Katiyar");		
		tempuser.setPassword("123");
		tempuser.setAddress("Kanpur");
		tempuser.setContactNo("9000765432");
		tempuser.setRole("ADMIN");
		tempuser.setGender("Male");
		authService.registerUser(tempuser);
	});
	}
	
	@Test	
	void registerUserTestInvalidContactNumber() throws RuntimeException{	
		Assertions.assertThrows(ContactNumberAlreadyExistException.class, () -> {		   
		tempuser.setEmail("pkatiyarx@gmail.com");
		tempuser.setFirstName("Pravin");
		tempuser.setLastName("Katiyar");		
		tempuser.setPassword("123");
		tempuser.setAddress("Kanpur");
		tempuser.setContactNo("9981655670");
		tempuser.setRole("ADMIN");
		tempuser.setGender("Male");
		authService.registerUser(tempuser);
	});
	}
	
	@Test
	void deleteuser() {
		userRepository.delete(tempuser2);
		
	}
}
