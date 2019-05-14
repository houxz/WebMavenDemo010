package com.projectmanage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

	@Autowired
	private SessionRegistry		sessionRegistry;
	
	public Boolean isDuplicateLogin(String username) {
		Boolean ret = false;
		System.out.println("========SessionService:isDuplicateLogin()============");
		
		return ret;
	}
	
	public Boolean KickOutUser(String username) {
		Boolean ret = false;
		System.out.println("======SessionService:KickOutUser()================");
		return ret;
	}
}
