package com.capgemini.onlinefoodordersys.model;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "track_order")
public class Trackorder {

	@Id
	@GeneratedValue
	private long trackOrderId;
	
	private Userorder userorder;
	
	private DeliveryGuy deliveryGuy;
	
	private String status;
	
	private String deliveryAddress;

	public long getTrackOrderId() {
		return trackOrderId;
	}

	public void setTrackOrderId(long trackOrderId) {
		this.trackOrderId = trackOrderId;
	}

	public Userorder getUserorder() {
		return userorder;
	}

	public void setUserorder(Userorder userorder) {
		this.userorder = userorder;
	}

	public DeliveryGuy getDeliveryGuy() {
		return deliveryGuy;
	}

	public void setDeliveryGuy(DeliveryGuy deliveryGuy) {
		this.deliveryGuy = deliveryGuy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}
	
	
	
	
	
}
