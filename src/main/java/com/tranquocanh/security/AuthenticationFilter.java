package com.tranquocanh.security;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticationFilter {

	String urlRedirect(HttpServletRequest request) ;
	
	static AuthenticationFilter of(String userName, String password) {
		return new AuthenticationImpl( userName,  password);
	}
	
}
