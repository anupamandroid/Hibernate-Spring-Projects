package com.anupamghosh.shopkart.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.anupamghosh.shopkart.util.FileUploadUtility;
import com.anupamghosh.shopkart.validator.ProductValidator;
import com.anupamghosh.shopkartbackend.dao.CategoryDAO;
import com.anupamghosh.shopkartbackend.dao.ProductDAO;
import com.anupamghosh.shopkartbackend.dto.Category;
import com.anupamghosh.shopkartbackend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation){
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title","Manage Products");
		mv.addObject("userCickManageProducts", true);
		
		Product nProduct = new Product();
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product", nProduct);
		
		if(operation!=null){
			if(operation.equals("product")){
				mv.addObject("message","Product Submitted Successfully!");
			}else if(operation.equals("category")){
				mv.addObject("message","Category Submitted Successfully!");
			}
		}
		
		return mv;
	}
	
	//Handling Product Submission
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mproduct, BindingResult results, Model model, HttpServletRequest request){
		
		new ProductValidator().validate(mproduct, results);
		
		if(results.hasErrors()){
			model.addAttribute("title","Manage Products");
			model.addAttribute("userCickManageProducts", true);
			model.addAttribute("message", "Sorry, Product not submitted successfully!");
			
			return "page";
		}
		
		logger.info(mproduct.toString());
		
		productDAO.addProduct(mproduct);
		
		if(!mproduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request, mproduct.getFile(), mproduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	//Handling Category Submission
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category){
		
		categoryDAO.addCategory(category);
		
		return "redirect:/manage/products?operation=category";
	}
	
	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.GET)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id){
		Product product = productDAO.getProduct(id);
		//Product Current Status as per DB
		boolean isActive = product.isActive();
		//Activate or Deactivate based on is_active field
		product.setActive(!isActive);
		//update the product in DB
		productDAO.updateProduct(product);
				
		return (isActive) ? "You have successfully deactivated the product with id " + product.getId() :
							"You have successfully activated the product with id " + product.getId();
	}
	
	
	
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		return categoryDAO.categoryList();
	}
	
	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}
}
