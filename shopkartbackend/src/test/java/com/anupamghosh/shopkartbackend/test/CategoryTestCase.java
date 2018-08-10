package com.anupamghosh.shopkartbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.anupamghosh.shopkartbackend.dao.CategoryDAO;
import com.anupamghosh.shopkartbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;
	
	@BeforeClass
	public static void init(){
		context = new AnnotationConfigApplicationContext();
		context.scan("com.anupamghosh.shopkartbackend");
		System.out.println("Before Refresh");
		context.refresh();
		System.out.println("After Refresh");
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
	}
	
	
	
	/*
	 * Checking whether a Single Category getting added or not
	 * */
	/*@Test
	public void testAddCategory(){
		category = new Category();
		
		category.setName("Laptop");
		category.setDescription("This is a laptop");
		category.setImageURL("pic_1.jpg");
		
		assertEquals("Successfully added a category inside Category table", true, categoryDAO.addCategory(category));
	}*/
	
	
	
	/*
	 * Checking whether a Single Category getting fetched based on name or not
	 * */
	/*@Test
	public void testGetCategory(){
		category = categoryDAO.getCategory(0);
		
		assertEquals("Successfully fetched single category from Category table", "Television", category.getName());
	}*/
	
	
	
	/*
	 * Checking whether a Single Category getting fetched based on name or not
	 * */
	/*@Test
	public void testUpdateCategory(){
		category = categoryDAO.getCategory(0);
		
		category.setName("TV");
		
		assertEquals("Successfully updated single existing category in Category table", true, categoryDAO.updateCategory(category));
	}*/
	
	
	
	/*
	 * Checking whether a Single Category getting deleted based on ID or not
	 * */
	/*@Test
	public void testDeleteCategory(){
		category = categoryDAO.getCategory(0);
		
		assertEquals("Successfully deleted single existing category in Category table", true, categoryDAO.deleteCategory(category));
	}*/
	
	
	
	/*
	 * Checking whether a list of Active Categories getting fetched or not
	 * */
	/*@Test
	public void testListCategory(){
		category = categoryDAO.getCategory(0);
		
		assertEquals("Successfully fetched the list of categories from Category table", 3, categoryDAO.categoryList().size());
	}*/
	
	
	
	
	/*
	 * Checking whether CRUD operation working properly or not
	 * */
	@Test
	public void testCRUDCategory(){
		//Adding Single Category
		category = new Category();
		
		category.setName("Television");
		category.setDescription("This is a television");
		category.setImageURL("pic_1.jpg");
		
		assertEquals("Successfully added a category inside Category table", true, categoryDAO.addCategory(category));
		
		
		
		category = new Category();
		
		category.setName("Laptop");
		category.setDescription("This is a laptop");
		category.setImageURL("pic_2.jpg");
		
		assertEquals("Successfully added a category inside Category table", true, categoryDAO.addCategory(category));
		
		
		//Fetching and Updating a particular Category
		
		category = categoryDAO.getCategory(0);
		
		category.setName("TV");
		
		assertEquals("Successfully updated single existing category in Category table", true, categoryDAO.updateCategory(category));
		
		
		//Deleting a particular Category
		
		category = categoryDAO.getCategory(0);
		
		assertEquals("Successfully deleted single existing category in Category table", true, categoryDAO.deleteCategory(category));
		
		
		//Fetching the list of Active Categories
		
		category = categoryDAO.getCategory(0);
		
		assertEquals("Successfully fetched the list of categories from Category table", 1, categoryDAO.categoryList().size());	
	}
}
