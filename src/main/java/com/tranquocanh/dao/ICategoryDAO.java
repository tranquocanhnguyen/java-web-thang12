package com.tranquocanh.dao;

import java.util.List;

import com.tranquocanh.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{

	List<CategoryModel> findAll();
	CategoryModel findOne(Long id);
	CategoryModel findOneByCode(String code);
}
