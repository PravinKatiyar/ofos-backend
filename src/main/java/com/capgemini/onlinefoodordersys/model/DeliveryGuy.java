package com.capgemini.onlinefoodordersys.model;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


	@Entity
	@Table(name = "delivery_guy")
	public class DeliveryGuy implements Serializable {
		/**
		 * 
		 */
		private static final long serialVersionUID = 8736554796212043430L;
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "guy_id")	
		private Long guyId;
		
		@Column(name = "guy_name")
		private String guyName;
		
		@Column(name = "guy_phone")
		private String guyPhone;
		
		@Column(name = "availability")
		private String availability;
	
	//	@OneToMany(mappedBy = "deliveryGuy")// Apply appropriate cascade Type
	//	private List<Userorder> userorder;

		public Long getGuyId() {
			return guyId;
		}

		public void setGuyId(Long guyId) {
			this.guyId = guyId;
		}

		public String getGuyName() {
			return guyName;
		}

		public void setGuyName(String guyName) {
			this.guyName = guyName;
		}

		public String getGuyPhone() {
			return guyPhone;
		}

		public void setGuyPhone(String guyPhone) {
			this.guyPhone = guyPhone;
		}

		public String getAvailability() {
			return availability;
		}

		public void setAvailability(String availability) {
			this.availability = availability;
		}

//		public List<Userorder> getUserorder() {
//			return userorder;
//		}
//
//		public void setUserorder(List<Userorder> userorder) {
//			this.userorder = userorder;
//		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}
 
		
		
		
	}


