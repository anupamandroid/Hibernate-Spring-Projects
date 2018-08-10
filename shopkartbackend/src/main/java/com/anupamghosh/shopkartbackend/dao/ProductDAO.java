package com.anupamghosh.shopkartbackend.dao;

import java.util.List;

import com.anupamghosh.shopkartbackend.dto.Product;

public interface ProductDAO {

	List<Product> productList();
	Product getProduct(int id);
	boolean addProduct(Product product);
	boolean updateProduct(Product product);
	boolean deleteProduct(Product product);
	
	//Business Methods
	List<Product> listActiveProducts();
	List<Product> listActiveProductsByCategory(int categoryId);
	List<Product> getLatestActiveProducts(int count);
}
