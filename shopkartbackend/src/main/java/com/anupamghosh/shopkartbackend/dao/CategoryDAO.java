package com.anupamghosh.shopkartbackend.dao;

import java.util.List;

import com.anupamghosh.shopkartbackend.dto.Category;

public interface CategoryDAO {
	List<Category> categoryList();
	Category getCategory(int id);
	boolean addCategory(Category category);
	boolean updateCategory(Category category);
	boolean deleteCategory(Category category);
}
