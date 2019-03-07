package com.antra.springboot.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.antra.springboot.entity.UserEntity;
import com.antra.springboot.exception.UserNotFoundException;
import com.antra.springboot.service.UserService;
import com.antra.springboot.vo.PagedResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(value = "User", description = "REST API for Users", tags={"User"})
public class GetUserController {
private UserService userService;
	
	@Autowired
	public GetUserController(UserService theUserService) {
		// TODO Auto-generated constructor stub
		userService = theUserService;
	}
	//expose "/user" and return list
	
	@GetMapping("/users")
	public List<UserEntity> findAll(){
		return userService.findAll();
	}
	
	
	@ApiOperation(value = "get users accordingly")
	@RequestMapping(value = "/alluser",  method = RequestMethod.GET)
	public ResponseEntity<PagedResponse<UserEntity>> getUserPagenation(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
														   @RequestParam(required = false, defaultValue = "5") Integer rows,
														   @RequestParam(required = false, defaultValue = "name") String orderBy) {

		PagedResponse<UserEntity> users = userService.findPaginated(pageNo, rows, orderBy);
		if (users.isEmpty()) {
			throw new UserNotFoundException("USER_NOT_FOUND");
		}
		return new ResponseEntity<PagedResponse<UserEntity>>(users, HttpStatus.OK);

	}
	
	//add mapping for get /users/{userId}
	@GetMapping("/users/{userId}")
	public UserEntity getUser(@PathVariable Integer userId) {
		UserEntity theUserEntity = userService.findById(userId);
		if (theUserEntity == null)
		      throw new UserNotFoundException("id-" + userId);

		return theUserEntity;
	}
	
}
