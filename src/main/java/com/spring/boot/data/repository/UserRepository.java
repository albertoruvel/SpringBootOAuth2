package com.spring.boot.data.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;


public interface UserRepository extends CrudRepository<User, Long>{
	public User findByLogin(String login); 
}
