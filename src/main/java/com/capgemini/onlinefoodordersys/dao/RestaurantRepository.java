package com.capgemini.onlinefoodordersys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinefoodordersys.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,Integer>{

}
