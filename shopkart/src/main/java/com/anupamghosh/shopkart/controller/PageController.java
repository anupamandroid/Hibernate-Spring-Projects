package com.anupamghosh.shopkart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.anupamghosh.shopkart.exception.ProductNotFoundException;
import com.anupamghosh.shopkartbackend.dao.CategoryDAO;
import com.anupamghosh.shopkartbackend.dao.ProductDAO;
import com.anupamghosh.shopkartbackend.dto.Category;
import com.anupamghosh.shopkartbackend.dto.Product;

/**
 * @author Professional
 *
 */

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value = {"/", "/index", "/home"})
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		mv.addObject("userClickHome", true);
		
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");
		
		//passing the categoryList
		mv.addObject("categories", categoryDAO.categoryList());
		
		return mv;
	}
	
	@RequestMapping(value = "/about")
	public ModelAndView about(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		
		return mv;
	}
	
	@RequestMapping(value = "/contact")
	public ModelAndView contact(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		
		return mv;
	}
	
	
	
	/*
	 * Methods to load all the products and based on category
	 * 
	 * */
	
	
	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		mv.addObject("userClickAllProducts", true);
		
		//passing the categoryList
		//mv.addObject("categories", categoryDAO.categoryList());
		
		return mv;
	}
	
	@RequestMapping(value = "/show/category/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id){
		ModelAndView mv = new ModelAndView("page");
		
		//CategoryDAO to fetch single Category
		Category category = null;
		
		category = categoryDAO.getCategory(id);
		
		//passing the categoryList
		//mv.addObject("categories", categoryDAO.categoryList());
				
		//passing the single category
		mv.addObject("category", category);
		
		mv.addObject("title", category.getName());
		mv.addObject("userClickCategoryProducts", true);
		
		return mv;
	}
	
	
	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException {
		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.getProduct(id);
		
		if(product == null) throw new ProductNotFoundException();
		
		//Update View Count
		product.setViews(product.getViews() + 1);
		productDAO.updateProduct(product);
		
		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);
		
		return mv;
	}
	
	/*--------- Webflow -----------*/
	//having similar mapping to our flow id (here, register is inbuilt flow id)
	//it will trigger register flow, not register mapping
	@RequestMapping(value = "/register")
	public ModelAndView register(){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		
		return mv;
	}
	
	/*--------- Spring Security -------*/
	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "error", required = false) String error, @RequestParam(name = "logout", required = false) String logout){
		ModelAndView mv = new ModelAndView("login");
		
		if(error != null){
			mv.addObject("message", "Invalid Credentials!");
		}
		
		if(logout != null){
			mv.addObject("logout", "User has successfully logged out!");
		}
		
		mv.addObject("title", "Login");
		
		return mv;
	}
	
	/*------ Access Denied Page for Unauthorized Access ---------*/
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied(){
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403 - Access Denied");
		mv.addObject("errorTitle", "Aha! Caught You.");
		mv.addObject("errorDescription", "You are not authorized to view this page.");
		
		return mv;
	}
	
	/*----------- Logout -----------*/
	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response){
		//to fetch the Authentication
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if(auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		
		return "redirect:/login?logout";
	}
	
	
	
	
	
	
	// URL Pattern ---- http://localhost:8080/shopkart/test1?pageName=Any Value
	
	@RequestMapping(value = "/test1")
	public ModelAndView test1(@RequestParam(value="pageName", required=false) String pageName){
		if(pageName == null){
			pageName = "RequestParam";
		}
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("pageName", pageName);
		
		return mv;
	}
	
	// URL Pattern ---- http://localhost:8080/shopkart/test2/Any Value
	
	@RequestMapping(value = "/test1/{pageName}")
	public ModelAndView test2(@PathVariable(value="pageName", required=false) String pageName){
		if(pageName == null){
			pageName = "PathVariable";
		}
		
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("pageName", pageName);
		
		return mv;
	}
}
