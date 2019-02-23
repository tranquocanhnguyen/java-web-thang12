package com.tranquocanh.service.impl;

import com.tranquocanh.dao.IUserDAO;
import com.tranquocanh.dao.impl.UserDAO;
import com.tranquocanh.model.UserModel;
import com.tranquocanh.service.IUserService;

public class UserService implements IUserService{

	IUserDAO userDao ; 
	
	public UserService() {
		userDao = new UserDAO();
	}
	
	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status) {
		// TODO Auto-generated method stub
		return userDao.findByUserNameAndPasswordAndStatus(userName, password, status);
	}

}
