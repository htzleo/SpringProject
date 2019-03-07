package com.antra.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antra.springboot.entity.UserEntity;
import com.antra.springboot.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(value = "User", description = "REST API for Users", tags={"User"})
public class PutUserController {
	
	private UserService userService;
	
	@Autowired
	public PutUserController(UserService theUserService) {
		// TODO Auto-generated constructor stub
		userService = theUserService;
	} 
	
	// add mapping for PUT /users - update existing user
	
			@PutMapping("/users")
			public ResponseEntity<Object> updateUser(@RequestBody UserEntity theUserEntity) {
				if(theUserEntity == null)
					return ResponseEntity.notFound().build();
				
				userService.saveUser(theUserEntity);
				
				return ResponseEntity.noContent().build();
			}
}
