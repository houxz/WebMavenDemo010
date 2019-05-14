package com.projectmanage.auth;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class CustomerAuthenticationProvider implements AuthenticationProvider{
	private CustomUserDetailsService customUserDetailsService;
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
		System.out.println("========CustomerAuthenticationProvider::authenticate()==================");
		
		String msg = new String();
		String username = new String();
		String password = new String();
		try {
			username = authentication.getName();
			password = authentication.getCredentials().toString();
			
			System.out.println(" User :" + username +"try login with password: " + password);
			
			CustomUserDetails userDetails = this.customUserDetailsService.loadUserByUsername( authentication.getName());
			
			if(userDetails.getAuthorities().size() <= 0) {
				throw new DisabledException(new String());
			}
			
			return new UsernamePasswordAuthenticationToken( userDetails, authentication.getCredentials(),
					userDetails.getAuthorities() );
			
		}catch(UsernameNotFoundException e) {
			
		}
		
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		System.out.println("========CustomerAuthenticationProvider::supports()==================");
		
		return true;// true ? false differnet is ? by hxz
	}

	public void setCustomUserDetailsService(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService = customUserDetailsService;
	}
}
