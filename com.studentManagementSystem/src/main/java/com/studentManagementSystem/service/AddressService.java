package com.studentManagementSystem.service;

import java.util.List;

import com.studentManagementSystem.model.Address;

public interface AddressService {
	
	public Address addAddress(Address add);
	public Address updateAddress(Address add);
	public Address removeAddress(Integer id);
	public Address viewAddress(Integer addressId);
	public List<Address> viewAllAddress();
	

}

