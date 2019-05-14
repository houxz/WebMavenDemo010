package com.projectmanage.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

import com.projectmanage.common.RoleType;
import com.projectmanage.dao.projectmanager.UserRoleModelDao;
import com.projectmanage.pojo.AuthorityModel;
import com.projectmanage.service.EmapgoAccountService;
import com.projectmanage.service.SessionService;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private EmapgoAccountService emapgoAccountService;
	
	@Autowired
	private UserRoleModelDao userRoleModelDao;
	
	@Autowired
	private SessionService sessionService;

	public CustomUserDetails   loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
System.out.println("=======CustomUserDetailsService:loadUserByUsername()=======================");
		
		if( sessionService.isDuplicateLogin(username) ) {
			throw new SessionAuthenticationException(new String());
		}
		//根据姓名查询 用户数据库中用户信息
		AuthorityModel authority = emapgoAccountService.getAuthorityByUsername(username);
		if( authority == null) {
			throw new UsernameNotFoundException(new String());
		}
		
		CustomUserDetails userDetails = new CustomUserDetails();
		userDetails.setUsername(username);
		userDetails.setPassword(authority.getPassword());
		userDetails.setEnable(authority.getEnabled().equals(1));
		
		List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		
//		Map<String, Object> auth = new HashMap<String,Object>();
//		auth.put("rolename", RoleType.ROLE_POIVIDEOEDIT);//byhxz bug
//		auths.add( new SimpleGrantedAuthority(MapUtils.getString(auth, "rolename")));
		
		Integer userid = authority.getId();
		
		//查询用户有那些角色
		List<Map<String,Object>> authlist  = userRoleModelDao.getEpleRoles(userid);
		for( Map<String,Object> auth : authlist) {
System.out.println("=======loadUserByUsername(): " + MapUtils.getString( auth, "rolename"));
			
			auths.add( new SimpleGrantedAuthority(MapUtils.getString( auth, "rolename")));
		}
		//int s1= userDetails.getAuthorities().size();
		userDetails.setAuthorities(auths);
		int s2 = userDetails.getAuthorities().size();
		
		return userDetails;
	}
}
