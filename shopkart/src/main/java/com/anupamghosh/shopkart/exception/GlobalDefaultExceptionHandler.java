package com.anupamghosh.shopkart.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalDefaultExceptionHandler {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNoHandlerFoundException(){
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "404 Error Page");
		mv.addObject("errorTitle", "The Page is not Constructed!");
		mv.addObject("errorDescription", "The page you are looking for is not available!");
		
		return mv;
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException(){
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "Product Unavailable");
		mv.addObject("errorTitle", "Product Not Available!");
		mv.addObject("errorDescription", "The product you are looking for is not available right now!");
		
		return mv;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex){
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "Error");
		mv.addObject("errorTitle", "Contact Your Administrator!");
		mv.addObject("errorDescription", ex.toString());
		
		return mv;
	}
}
