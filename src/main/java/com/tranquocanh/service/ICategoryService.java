package com.tranquocanh.service;

import java.util.List;

import com.tranquocanh.model.CategoryModel;

public interface ICategoryService {

	public List<CategoryModel> findAll();
	CategoryModel findOne(Long id);
}
