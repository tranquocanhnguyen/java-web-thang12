package com.tranquocanh.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.tranquocanh.model.RoleModel;
import com.tranquocanh.model.UserModel;

public class UserMapper implements RowMapper<UserModel>{

	@Override
	public UserModel mapRow(ResultSet rs) {
		// TODO Auto-generated method stub
		UserModel model =  new UserModel();
		try {
				model.setId(rs.getLong("id"));
				model.setUserName(rs.getString("username"));
				model.setFullName(rs.getString("fullname"));
				model.setPassword(rs.getString("password"));
				model.setStatus(rs.getInt("status"));
				RoleModel role =new RoleModel();
				try {
					role.setCode(rs.getString("code"));
					role.setName(rs.getString("name"));
					model.setRole(role);
				} catch (Exception e) {
					e.printStackTrace();
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return model;
	}

	

}
