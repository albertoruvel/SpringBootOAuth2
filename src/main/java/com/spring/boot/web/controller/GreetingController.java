package com.spring.boot.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.boot.web.dto.Greeting;

@RestController
public class GreetingController {
	@RequestMapping("sayHi")
	public String sayHi(){
		return "Hi there from a spring controller"; 
	}
	
	
	@RequestMapping(value="greeting", produces={ MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody Greeting getGreeting(){
		return new Greeting("Hi from a greeting object"); 
	}
}
