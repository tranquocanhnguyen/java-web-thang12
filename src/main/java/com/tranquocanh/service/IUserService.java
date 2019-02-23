package com.tranquocanh.service;

import com.tranquocanh.model.UserModel;

public interface IUserService {

	UserModel findByUserNameAndPasswordAndStatus(String userName, String password, Integer status);
}
