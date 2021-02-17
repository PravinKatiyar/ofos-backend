package com.capgemini.onlinefoodordersys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.onlinefoodordersys.model.DeliveryGuy;
import com.capgemini.onlinefoodordersys.model.User;

public interface DeliveryGuyRepository extends JpaRepository<DeliveryGuy,Long>{
	
	public DeliveryGuy findDeliveryGuyByGuyPhone(String guyPhone);


	public DeliveryGuy findDeliveryGuyByGuyId(long id);
	
	
//	 @Query(value= "SELECT * From delivery_guy d WHERE d.availability="Avaliavle" ",nativeQuery=true)
//	 public String findAddress(long orderId);
	


	


}
