package com.spring.boot.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.spring.boot.data.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	private final UserRepository userRepository;
	
	@Autowired
	public CustomUserDetailsService(UserRepository repo){
		this.userRepository = repo; 
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(username);
		if(user == null)
			throw new UsernameNotFoundException(String.format("User %s does not exists", username)); 
		return new UserRepositoryUserDetails(user); 
		
	}
	
	static final class UserRepositoryUserDetails extends User implements UserDetails{
		
		private static final long serialVersionUID = 1L;

		private UserRepositoryUserDetails(User user){
			super(user.getUsername(), user.getPassword(), null); 
		}
		
		@Override 
		public Collection<GrantedAuthority> getAuthorities(){
			return super.getAuthorities();
		}
		
		@Override 
		public String getUsername(){
			return super.getUsername(); 
		}

		@Override
		public String getPassword() {
			return super.getPassword(); 
		}

		@Override
		public boolean isEnabled() {
			return true; 
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true; 
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return super.isCredentialsNonExpired();
		}
		
		
	}

}
