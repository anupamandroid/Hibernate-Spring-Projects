package com.anupamghosh.shopkartbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.anupamghosh.shopkartbackend.dao.ProductDAO;
import com.anupamghosh.shopkartbackend.dto.Category;
import com.anupamghosh.shopkartbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	/*
	 * Fetch a list of Products */
	@Override
	public List<Product> productList() {
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	/*
	 * Fetch a Single Product */
	
	@Override
	public Product getProduct(int id) {
		try{
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(id));
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * Add a Single Product */
	@Override
	public boolean addProduct(Product product) {
		try{
			sessionFactory.getCurrentSession().persist(product);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Update an existing Single Product */
	@Override
	public boolean updateProduct(Product product) {
		try{
			sessionFactory.getCurrentSession().update(product);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Delete an existing Single Product */
	@Override
	public boolean deleteProduct(Product product) {
		product.setActive(false);
		
		try{
			sessionFactory.getCurrentSession().update(product);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * Fetch Active Product List */
	@Override
	public List<Product> listActiveProducts() {
		String selectActiveProducts = "FROM Product WHERE active = :active";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class).setParameter("active", true).getResultList();
	}

	/*
	 * Fetch Active Product List by Category */
	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
		String selectActiveProductsByCategory = "FROM Product WHERE is_active = :active AND category_id = :categoryID";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProductsByCategory, Product.class).setParameter("active", true).setParameter("categoryID", categoryId).getResultList();
	}

	/*
	 * Fetch Latest Active Product List */
	@Override
	public List<Product> getLatestActiveProducts(int count) {
		String selectActiveProducts = "FROM Product WHERE active = :active ORDER BY id";
		return sessionFactory.getCurrentSession().createQuery(selectActiveProducts, Product.class).setParameter("active", true).setFirstResult(0).setMaxResults(count).getResultList();
	}

}
