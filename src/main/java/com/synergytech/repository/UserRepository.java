package com.synergytech.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.synergytech.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Page<User> findAll(Pageable pageable); 		// Support pagination
	

}
