package com.synergytech.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.synergytech.entity.User;
import com.synergytech.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User saveUser(User user) {
		return userRepository.save(user);
	}

//	public List<User> getAllUsers() {
//		List<User> users = userRepository.findAll();
//		return users;
//	}
	
	public Page<User> getAllUsers(Pageable pageable){
		return userRepository.findAll(pageable);
	}
	
	
	
	
	
	public Optional<User> getUserById(Long id){
		 return userRepository.findById(id);
	}
	
	
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}









