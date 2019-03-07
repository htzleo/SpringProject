package com.antra.springboot.test;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.antra.springboot.controller.GetUserController;
import com.antra.springboot.entity.UserEntity;
import com.antra.springboot.service.UserService;


import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.*;

import org.mockito.Mockito;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestUserService {
	@Mock
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    @Before
    public void setUp(){
    	
        
        MockitoAnnotations.initMocks(this);
        RestAssuredMockMvc.standaloneSetup(new GetUserController(userService));
   
        
    }
    
    /// Test 1
    @Test
    public void findOneTest() {
//        UserEntity user = userService.findById(10);
//        logger.info("user.name = {}",user.getName());
//        Assert.assertEquals(user.getName(), "john");
    	given().get("http://localhost:8080/api/users/2").then().assertThat().statusCode(200);
    }
    /// Test 2
    @Test
    public void testGetUserFromDB(){
        Mockito.when(userService.findById(anyInt())).thenReturn(new UserEntity(1,"test name", 20, 10000d));
        given().accept("application/json").get("/api/user/1").peek().
                then().assertThat()
                .statusCode(200)
                .body(Matchers.equalTo("{\"id\":1,\"name\":\"test name\",\"age\":20,\"salary\":10000.0}"));
    }
    
    @Test
    public void createUserButExceptionRaised(){
    	UserEntity testUser = new UserEntity(null,"test name", 20, 10000d);
        UserEntity savedUser = new UserEntity(1,"test name", 20, 10000d);
        Mockito.when(userService.saveUser(anyObject())).thenThrow(new RuntimeException("dummy error"));
        given().accept("application/json").contentType("application/json").body(testUser).post("/api/user").peek().
                then().assertThat()
                .statusCode(500)
                .body("message",Matchers.is("dummy error"));
    }
    
    
}
