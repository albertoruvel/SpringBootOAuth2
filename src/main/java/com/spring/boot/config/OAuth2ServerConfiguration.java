package com.spring.boot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class OAuth2ServerConfiguration {
	public static final String RESOURCE_ID = "restservice";
	
	@Configuration
	@EnableResourceServer
	protected static class ResourceServerConfiguration
									extends ResourceServerConfigurerAdapter{

		@Override
		public void configure(HttpSecurity http) throws Exception {
			http
				.authorizeRequests()
					.antMatchers("/users")
						.hasRole("ADMIN")
					.antMatchers("hi")
						.authenticated(); 
			
			//bypass the h2Console context path 
			http
				.authorizeRequests()
				.antMatchers("h2Console/**")
				.permitAll().and()
				.headers()
				.frameOptions()
				.disable(); 
			
			super.configure(http); 
		}
		
	}
}
