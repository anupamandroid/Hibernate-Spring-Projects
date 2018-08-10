package com.anupamghosh.shopkartbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.anupamghosh.shopkartbackend.dao.UserDAO;
import com.anupamghosh.shopkartbackend.dto.Address;
import com.anupamghosh.shopkartbackend.dto.Cart;
import com.anupamghosh.shopkartbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user = null;
	private Cart cart = null;
	private Address address = null;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.anupamghosh.shopkartbackend");
		context.refresh();
		
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
	//One To One Relationship
	
	/*@Test
	public void testAdd(){
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hrithik@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		// add the user
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
		
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		// linked the address with the user
		address.setUserId(user.getId());
		
		// add the billing address
		assertEquals("Failed to add the billing address!", true, userDAO.addAddress(address));
		
		
		if(user.getRole().equals("USER")){
			//create a cart for this user
			cart = new Cart();
			//cart.setUserid(user.getId());	//Normal Case
			cart.setUser(user);
			
			//add a cart
			assertEquals("Failed to add the cart!", true, userDAO.addCart(cart));	
			
			//add a shipping address for this user
			address = new Address();
			address.setAddressLineOne("301/B Jadoo Society, King Uncle Nagar");
			address.setAddressLineTwo("Near Store");
			address.setCity("Mumbai");
			address.setState("Maharashtra");
			address.setCountry("India");
			address.setPostalCode("400001");
			address.setShipping(true);
			
			// linked the address with the user
			address.setUserId(user.getId());
			
			// add the shipping address
			assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
		}
	}*/
	
	
	
	
	
	//One To One Bi-directional Relationship
	
	/*@Test
	public void testAdd(){
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hr@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("CUSTOMER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		
		if(user.getRole().equals("USER")){
			//create a cart for this user
			cart = new Cart();
			cart.setUser(user);
			
			//add a cart
			user.setCart(cart);
		}
		
		// add the user
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
	}*/
	
	/*@Test
	public void testUpdateCart(){
		//Fetch the user by email
		user = userDAO.getByUser("hrithik@gmail.com");
		
		//get the cart of the user
		cart = user.getCart();
		
		//update data of fetched cart
		cart.setGrandTotal(5555);
		cart.setCartLines(2);
		
		assertEquals("Failed to update the cart!", true, userDAO.updateCart(cart));
	}*/
	
	
	
	
	//Many To One Relationship
	
	/*@Test
	public void testAddAddress(){
		//we need to add the user
		
		user = new User() ;
		user.setFirstName("Hrithik");
		user.setLastName("Roshan");
		user.setEmail("hrithik.roshan@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setEnabled(true);
		user.setPassword("12345");
		
		// add the user
		assertEquals("Failed to add the user!", true, userDAO.addUser(user));
		
		
		//we are going to add the billing address
		
		address = new Address();
		address.setAddressLineOne("101/B Jadoo Society, Krissh Nagar");
		address.setAddressLineTwo("Near Kaabil Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setBilling(true);
		
		//attached the user to the address
		
		address.setUser(user);
		
		assertEquals("Failed to add the biiling address!", true, userDAO.addAddress(address));
		
		
		
		//we are also going to add the shipping address
		address = new Address();
		address.setAddressLineOne("301/B Jadoo Society, King Uncle Nagar");
		address.setAddressLineTwo("Near Store");
		address.setCity("Mumbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setShipping(true);
		
		//attached the user to the address
		
		address.setUser(user);
				
		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
	}*/
	
	
	@Test
	public void testAddAddress(){
		user = userDAO.getByUser("hrithik.roshan@gmail.com");
		
		//we are also going to add the shipping address
		address = new Address();
		address.setAddressLineOne("301/B Jadoo Society, King Uncle Nagar");
		address.setAddressLineTwo("Near Store");
		address.setCity("Bangalore");
		address.setState("Karnataka");
		address.setCountry("India");
		address.setPostalCode("400001");
		address.setShipping(true);
		
		//attached the user to the address
		address.setUser(user);
				
		assertEquals("Failed to add the shipping address!", true, userDAO.addAddress(address));
	}
	
	@Test
	public void testGetAddresses(){
		user = userDAO.getByUser("hrithik.roshan@gmail.com");
		
		assertEquals("Failed to fetch the list of shipping addresses!", 2, userDAO.getShippingAddresses(user));
		
		assertEquals("Failed to fetch the billing address!", true, userDAO.getBillingAddress(user));
	}
}
