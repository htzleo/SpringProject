package com.antra.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.antra.springboot.entity.UserEntity;
import com.antra.springboot.service.UserService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/api")
@Api(value = "User", description = "REST API for Users", tags={"User"})
public class DeleteUserController {
private UserService userService;
	
	@Autowired
	public DeleteUserController(UserService theUserService) {
		// TODO Auto-generated constructor stub
		userService = theUserService;
	}
	
	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable Integer userId) {
		
		UserEntity tempUser = userService.findById(userId);
		
		// throw exception if null
		
		if (tempUser == null) {
			throw new RuntimeException("User id not found - " + userId);
		}
		
		userService.deleteById(userId);
		
		return "Deleted user id - " + userId;
	}
}
