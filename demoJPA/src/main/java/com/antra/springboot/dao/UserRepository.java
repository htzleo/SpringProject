package com.antra.springboot.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.antra.springboot.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
	List<UserEntity> findAllById(Pageable pageable);
}
