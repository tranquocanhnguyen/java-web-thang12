package com.tranquocanh.dao.impl;

import java.util.List;

import com.tranquocanh.dao.ICategoryDAO;
import com.tranquocanh.mapper.CategoryMapper;
import com.tranquocanh.model.CategoryModel;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
	
	public List<CategoryModel> findAll() {
	
		String sql = "select * from category";
		return query(sql, new CategoryMapper());
	}

	@Override
	public CategoryModel findOne(Long id) {
		String sql  =  "select * from category where id = ?";
		return query(sql,new CategoryMapper(),id).get(0);
	}

    @Override
    public CategoryModel findOneByCode(String code) {
        String sql = "select * from category where code = ?";
		return query(sql, new CategoryMapper(), code).get(0);
    }

}
