package com.tranquocanh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tranquocanh.model.NewModel;

public class NewMapper implements RowMapper<NewModel> {

	@Override
	public NewModel mapRow(ResultSet rs) {
		NewModel news = new NewModel();
		try {
			news.setId(rs.getLong("id"));
			news.setTitle(rs.getString("title"));
			news.setThumbnail(rs.getString("thumbnail"));
			news.setShortDescription(rs.getString("shortDescription"));
			news.setContent(rs.getString("content"));
			news.setCategoryId(rs.getLong("categoryId"));
			news.setCreatedDate(rs.getTimestamp("createdDate"));
			news.setCreatedBy(rs.getString("createdBy"));
			if(rs.getTimestamp("modifiedDate") != null) {
				news.setModifiedDate(rs.getTimestamp("modifiedDate"));		
			}
			if(rs.getString("modifiedBy") != null) {
				news.setModifiedBy(rs.getString("modifiedBy"));	
			}
		} catch (SQLException e) {
			return null;
		}
		return news;
	}
}
