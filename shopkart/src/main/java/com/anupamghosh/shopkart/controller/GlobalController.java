package com.anupamghosh.shopkart.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.anupamghosh.shopkart.model.UserModel;
import com.anupamghosh.shopkartbackend.dao.UserDAO;
import com.anupamghosh.shopkartbackend.dto.User;

@ControllerAdvice
public class GlobalController {

	@Autowired
	private HttpSession session;
	
	private UserDAO userDAO;
	
	private UserModel userModel = null;
	
	@ModelAttribute("userModel")
	public UserModel getuserModel(){
		if(session.getAttribute("userModel") == null){
			//add the UserModel
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = userDAO.getByUser(authentication.getName());
			
			if(user != null){
				//add a new UserModel object to pass user details
				userModel = new UserModel();
				
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName()+" "+user.getLastName());
				
				if(userModel.getRole().equals("USER")){
					//set the cart only if the user is a buyer
					userModel.setCart(user.getCart());
				}
				
				//set the userModel in the session
				session.setAttribute("userModel", userModel);
				
				return userModel;
			}
		}
		
		return (UserModel) session.getAttribute("userModel");
	}
}
