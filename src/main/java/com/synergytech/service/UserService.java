package com.synergytech.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public List<User> getAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}
	
	public Optional<User> getUserById(Long id){
		 return userRepository.findById(id);
	}
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
}









