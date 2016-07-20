package com.spring.boot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.boot.service.CustomUserDetailsService;

public class CustomAuthenticationProvider implements AuthenticationProvider{

	
	@Autowired
	private CustomUserDetailsService userDetailsService; 
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		Authentication auth = null;
		
		String username = authentication.getName(); 
		String password = authentication.getCredentials().toString(); 
		
		UserDetails userDetails = userDetailsService.loadUserByUsername(username); 
		if(userDetails != null)
			auth = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities()); 
	
		if(auth == null || userDetails == null)
			throw new BadCredentialsException("Bad credentials"); 
		return auth;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication)); 
	}

}
