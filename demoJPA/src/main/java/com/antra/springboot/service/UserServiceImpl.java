package com.antra.springboot.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.antra.springboot.dao.UserRepository;
import com.antra.springboot.entity.UserEntity;
import com.antra.springboot.vo.PagedResponse;



@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	
	@Autowired
	public UserServiceImpl(UserRepository theUserRepository) {
		userRepository = theUserRepository;
	}
	
	@Override
	@Transactional
	public List<UserEntity> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
	

	@Override
	@Transactional
	public UserEntity findById(Integer theId) {
		// TODO Auto-generated method stub
		Optional<UserEntity> result = userRepository.findById(theId);
		
		UserEntity theUser = null;
		if(result.isPresent()) {
			theUser = result.get();
		}
		else {
			throw new RuntimeException("Not find user id - "+ theId);
		}
		return theUser;
	}

	@Override
	@Transactional
	public UserEntity saveUser(UserEntity user) {
		// TODO Auto-generated method stub
		UserEntity userEntity =  userRepository.save(new UserEntity(user.getId(), user.getName(), user.getAge(), user.getSalary()));
		return userEntity;
		
	}

	@Override
	@Transactional
	public void deleteById(Integer theId) {
		// TODO Auto-generated method stub
		userRepository.deleteById(theId);
	}

	
	@Override
	@Transactional
	public PagedResponse<UserEntity> findPaginated(int page, int size, String orderBy) {
		// TODO Auto-generated method stub
		Sort sort = null;
		if (orderBy != null) {
			sort = new Sort(Sort.Direction.ASC, orderBy);
		}
		Page<UserEntity> page1 = userRepository.findAll(new PageRequest(page, size, sort));
		List<UserEntity> list = page1.getContent();
		PagedResponse<UserEntity> result = new PagedResponse<>();
		result.setPage(page1.getNumber());
		result.setRows(page1.getSize());
		result.setTotalPage(page1.getTotalPages());
		result.setTotalElement(page1.getTotalElements());
		result.setOrder(page1.getSort().toString());
		result.setBody(list.stream().map(e -> new UserEntity(e.getId(),e.getName(), e.getAge(), e.getSalary()))
				.collect(Collectors.toList()));
		return result;
	}


	
	
	
	

}
