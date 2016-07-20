package com.spring.boot.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.spring.boot.security.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomAuthenticationProvider authProvider;
	
	@Override 
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Override 
	public void configure(HttpSecurity httpSecurity)throws Exception{
		httpSecurity
			.authorizeRequests()
			.antMatchers("/h2Console/**")
			.permitAll(); 
		
		httpSecurity
			.csrf()
				.disable()
			.headers()
				.frameOptions()
					.disable(); 
		super.configure(httpSecurity);
	}
	
	@Override 
	public void configure(AuthenticationManagerBuilder builder)throws Exception{
		builder.authenticationProvider(authProvider); 
	}
}
