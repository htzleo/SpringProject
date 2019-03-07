package com.antra.springboot.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antra.springboot.entity.UserEntity;
import com.antra.springboot.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(value = "User", description = "REST API for Users", tags={"User"})
public class PostUserContoller {
	
	private UserService userService;
	
	public PostUserContoller(UserService theUserService) {
		// TODO Auto-generated constructor stub
		userService = theUserService;
	}
	// add mapping for post /users -- add new user
	
		@PostMapping("/users")
		public UserEntity addUser(@RequestBody UserEntity theUserEntity){
			//in case they pass an id in JSON
			//Set id to 0, this is force a save of new item instead of update
			//theUserEntity.setId((long) 0);
			
			userService.saveUser(theUserEntity);
			
			return theUserEntity;
		}
		
}
