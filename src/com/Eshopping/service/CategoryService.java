package com.Eshopping.service;

import java.util.List;

import model.Category;

import com.Eshopping.dao.CategoryDao;


public class CategoryService {
	
	CategoryDao categoryDao=new CategoryDao();
	
	public boolean deleteCategory(int id) {
		return categoryDao.deleteCategory(id);
	}


	public Category findCategoryById(int parseInt) {
		return categoryDao.findCategoryById(parseInt);
	}


	public List<Category> findCategoryList(Category c) {
		return categoryDao.findCategoryList(c);
	}


	public int findCategoryCount() {
		return categoryDao.findCategoryCount();
	}


	public boolean updateCategory(Category c) {
		return categoryDao.updateCategory(c);
	}

}
