package com.tranquocanh.dao;

import com.tranquocanh.model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {

	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
