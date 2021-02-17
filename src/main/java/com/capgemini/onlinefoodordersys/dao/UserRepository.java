package com.capgemini.onlinefoodordersys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinefoodordersys.model.User;

@Repository 
public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmailIgnoreCase(String username);

	public User findUserByContactNo(String contactNo);

	public User findUserByEmail(String email);

	public User findUserById(Long id);
	
	public void deleteById(Long id);
	
	public void deleteByEmail(String email);

}
