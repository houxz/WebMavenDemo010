package com.projectmanage.ctrl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;

public class BaseCtrl {

	protected String getLoginAccount(HttpSession session) {
		System.out.println("========BaseCtrl:getLoginAccount()==============");
		SecurityContext sc = (SecurityContext)session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if(sc != null) {
			Authentication au = sc.getAuthentication();
			String account = au.getName();
			System.out.println("=account:"+ account);
			return account;
		}else {
			System.out.println("=account:null1");
			return "";
		}
	}
	
	protected boolean hasRole(HttpServletRequest request, String role) {
		SecurityContextHolderAwareRequestWrapper sch = new SecurityContextHolderAwareRequestWrapper(request,"");
		return sch.isUserInRole(role);
	}
}
