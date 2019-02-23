package com.tranquocanh.dao.impl;

import java.util.List;

import com.tranquocanh.dao.IUserDAO;
import com.tranquocanh.mapper.UserMapper;
import com.tranquocanh.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		StringBuilder sql = new StringBuilder("select * from user as u ");
		sql.append("inner join role as r ");
		sql.append("on u.roleid = r.id ");
		sql.append("where username = ? and password = ? and status = ?");
		List<UserModel> list = query(sql.toString(), new UserMapper(), userName, password, status); 
		return list.isEmpty() ? null : list.get(0);
	}

}
