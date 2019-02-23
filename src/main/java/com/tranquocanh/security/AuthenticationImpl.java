package com.tranquocanh.security;

import com.tranquocanh.model.UserModel;
import com.tranquocanh.service.IUserService;
import com.tranquocanh.service.impl.UserService;
import com.tranquocanh.utils.SessionUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class AuthenticationImpl implements AuthenticationFilter {

	private String userName;
	private String password;
	
	private IUserService userService;
	
	public AuthenticationImpl(String userName, String password) {
		this.userName = userName;
		this.password = password;
		userService = new UserService();
	}

	@Override
	public String urlRedirect(HttpServletRequest request) {

		UserModel userModel = userService.findByUserNameAndPasswordAndStatus(this.userName,this.password,1);
		if (userModel != null) {
			SessionUtil.getInstance().putValue(request,"USER_MODEL",userModel);
			if (userModel.getRole().getName().equals("admin")) {
				return "admin-home";
			} else if (userModel.getRole().getName().equals("user")) {
				return "trang-chu";
			}
		} else {
			return "dang-nhap?action=login&message=username_password_incorrect&alert=danger";
		}
		return null;
	} 
}
