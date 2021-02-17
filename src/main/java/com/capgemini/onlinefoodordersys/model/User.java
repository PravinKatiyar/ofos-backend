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

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
@Component
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8736554796212043430L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "gender")
	private String gender;

	@Column(name = "address")
	private String address;

	@Column(name = "contactNo")
	private String contactNo;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "role")
	private String role;

	@Column(name = "wallet_balance")
	private float walletBalance;

	@OneToMany(mappedBy="user",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Userorder> userOrder;

	public List<Userorder> getUserOrder() {
		return userOrder;
	}

	public void setUserorder(List<Userorder> userOrder) {
		this.userOrder = userOrder;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnable(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public float getWalletBalance() {
		return walletBalance;
	}

	public void setWalletBalance(float walletBalance) {
		this.walletBalance = walletBalance;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public User(String firstName, String lastName, String email, String password, String gender, String address,
			String contactNo, boolean enabled, String role, float walletBalance) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.contactNo = contactNo;
		this.enabled = enabled;
		this.role = role;
		this.walletBalance = walletBalance;
	}
	

	public User() {
		super();
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", gender=" + gender + ", address=" + address + ", contactNo=" + contactNo
				+ ", enabled=" + enabled + ", role=" + role + ", walletBalance=" + walletBalance + ", userOrder="
				+ userOrder + "]";
	}
	
	

}
