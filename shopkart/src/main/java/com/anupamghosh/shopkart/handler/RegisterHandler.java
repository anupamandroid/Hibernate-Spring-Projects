package com.anupamghosh.shopkart.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.anupamghosh.shopkart.model.RegisterModel;
import com.anupamghosh.shopkartbackend.dao.UserDAO;
import com.anupamghosh.shopkartbackend.dto.Address;
import com.anupamghosh.shopkartbackend.dto.Cart;
import com.anupamghosh.shopkartbackend.dto.User;

@Component
public class RegisterHandler {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public RegisterModel init(){
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user){
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing){
		registerModel.setBilling(billing);
	}
	
	public String validateUser(User user, MessageContext error){
		String transitionValue = "success";
		
		//Checking if password matches confirm password
		if(!(user.getPassword().equals(user.getConfirmPassword()))){
			error.addMessage(new MessageBuilder().error().source("confirmPassword").defaultText("Password does not match the confirm password!").build());
			transitionValue = "failure";
		}
		
		if(userDAO.getByUser(user.getEmail()) != null){
			error.addMessage(new MessageBuilder().error().source("email").defaultText("Email address is already used!").build());
			transitionValue = "failure";
		}
		
		return transitionValue;
	}
	
	public String saveAll(RegisterModel registerModel){
		String transitionValue= "success";
		
		//Fetch the user
		User user = registerModel.getUser();
		
		if(user.getRole().equals("USER")){
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		
		//encode the password
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		
		//Save the user
		userDAO.addUser(user);
		
		//Get the address
		Address billing = registerModel.getBilling();
		billing.setUser(user);
		billing.setBilling(true);
		
		//Save the address
		userDAO.addAddress(billing);
		
		return transitionValue;
	}
}
