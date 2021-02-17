package com.capgemini.onlinefoodordersys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinefoodordersys.model.Trackorder;

@Repository
public interface TrackOrderRepository extends JpaRepository<Trackorder, Long> {

}
