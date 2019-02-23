package com.tranquocanh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tranquocanh.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {

	@Override
	public CategoryModel mapRow(ResultSet rs) {
		CategoryModel category = new CategoryModel();
		try {
			category.setId(rs.getLong("id"));
			category.setCode(rs.getString("code"));
			category.setName(rs.getString("name"));
		} catch (SQLException e) {
		  return null;
		}

		return category;
	}

}
