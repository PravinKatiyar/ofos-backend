package com.capgemini.onlinefoodordersys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.onlinefoodordersys.model.Userorder;

public interface OrderRepository extends JpaRepository<Userorder, Long> {
	
 @Query(value= "SELECT * From user_order o WHERE o.user_id=?",nativeQuery=true)
  public List<Userorder> findByUserId(long userId);

 @Query(value= "SELECT user_address From user_order o WHERE o.order_id=?",nativeQuery=true)
 public String findAddress(long orderId);

 @Query(value= "select * from user_order where order_status!='cancel'",nativeQuery=true)
public Iterable<Userorder> viewAllnonCancelledOrders();
 
 @Query(value= "SELECT * From user_order o WHERE o.order_id=?",nativeQuery=true)
 public Userorder findorderByorderId(long orderId);
 
	
	

}
