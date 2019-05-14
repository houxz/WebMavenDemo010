package com.projectmanage.auth;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.projectmanage.common.RoleType;

public class AuthMonitorInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestUrl = request.getRequestURI();
		System.out.println("=========AuthMonitorInterceptor::preHandle()==========" + requestUrl);
		
		Set<RoleType> auths = new HashSet<RoleType>();
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			for (GrantedAuthority ga : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
				System.out.println(ga.getAuthority());

				auths.add(RoleType.valueOf(ga.getAuthority()));
				
				return true;//有权限
			}
		} else {
			System.out.println("======AuthMonitorInterceptor::preHandle()==null=1=");
		}
		
		
		
		request.getRequestDispatcher("/exception/accessDenied.web").forward(request, response);
		return false;//无权限访问页面
	}
}
