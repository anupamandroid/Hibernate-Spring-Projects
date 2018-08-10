package com.anupamghosh.shopkartbackend.dao;

import java.util.List;

import com.anupamghosh.shopkartbackend.dto.Address;
import com.anupamghosh.shopkartbackend.dto.Cart;
import com.anupamghosh.shopkartbackend.dto.User;

public interface UserDAO {

	boolean addUser(User user);
	User getByUser(String email);
	
	boolean addAddress(Address address);
	Address getBillingAddress(User user);
	List<Address> getShippingAddresses(User user);
	
	//alernative used to reduce the no. of queries generated
	//Address getBillingAddress(int userId);
	//List<Address> getShippingAddresses(int userId);
	
	boolean addCart(Cart cart);
	boolean updateCart(Cart cart);
}
