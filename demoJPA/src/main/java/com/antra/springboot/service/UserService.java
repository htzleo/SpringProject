package com.antra.springboot.service;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.antra.springboot.entity.UserEntity;
import com.antra.springboot.vo.PagedResponse;


public interface UserService {
	public List<UserEntity> findAll();
	
	public UserEntity findById(Integer userId);
	
	public UserEntity saveUser(UserEntity theUserEntity);
	
	public void deleteById(Integer userId);
	
	public PagedResponse<UserEntity> findPaginated(int page, int size, String orderBy);
	
}
