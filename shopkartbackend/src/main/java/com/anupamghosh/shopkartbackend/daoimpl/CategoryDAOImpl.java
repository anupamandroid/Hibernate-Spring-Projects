package com.anupamghosh.shopkartbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.anupamghosh.shopkartbackend.dao.CategoryDAO;
import com.anupamghosh.shopkartbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	/*private static List<Category> categories = new ArrayList<>();
	
	static {
		Category category = null;
		
		//Adding 1st Category
		category = new Category();
				
		category.setId(1);
		category.setName("Television");
		category.setDescription("This is a television");
		category.setImageURL("pic_1.jpg");
		
		categories.add(category);
		
		//Adding 2nd Category
		category = new Category();
		
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("This is a mobile");
		category.setImageURL("pic_2.jpg");
		
		categories.add(category);
		
		//Adding 3rd Category
		category = new Category();
		
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("This is a laptop");
		category.setImageURL("pic_3.jpg");
		
		categories.add(category);
	}*/
	
	@Override
	public List<Category> categoryList() {
		String selectActiveCategory = "FROM Category WHERE is_active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", 1);
		
		return query.getResultList();
	}

	/*
	 * Getting a Single Category based on ID
	 * */
	@Override
	public Category getCategory(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	/*
	 * Adding a Single Category into Category table
	 * */
	@Override
	public boolean addCategory(Category category) {
		try{
			sessionFactory.getCurrentSession().persist(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		
	}

	/*
	 * Updating a Single Category based on ID
	 * */
	@Override
	public boolean updateCategory(Category category) {
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Deleting a SIngle Category based on ID
	 * */
	@Override
	public boolean deleteCategory(Category category) {
		category.setActive(false);
		
		try{
			sessionFactory.getCurrentSession().update(category);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

}
