package com.tranquocanh.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.tranquocanh.dao.ICategoryDAO;
import com.tranquocanh.model.CategoryModel;
import com.tranquocanh.service.ICategoryService;

public class CategoryService implements ICategoryService{

	@Inject
	private ICategoryDAO categoryDao;
	
	public List<CategoryModel> findAll() {
		// TODO Auto-generated method stub
		return categoryDao.findAll();
	}

	@Override
	public CategoryModel findOne(Long id) {
		return categoryDao.findOne(id);
	}

}
