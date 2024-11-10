package com.synergytech.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synergytech.entity.User;
import com.synergytech.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User savedUsers = userService.saveUser(user);
		return new ResponseEntity<>(savedUsers, HttpStatus.CREATED);
	}

//	@GetMapping
//	public ResponseEntity<List<User>> getAllUsers() {
//		List<User> users = userService.getAllUsers();
//		if (users.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//
//		}
//
//		return new ResponseEntity<>(users, HttpStatus.OK);
//	}

	@GetMapping
	public ResponseEntity<Page<User>> getAllUsers(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		Pageable pageable = PageRequest.of(page, size);
		Page<User> users = userService.getAllUsers(pageable);
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable Long id) {
		Optional<User> user = userService.getUserById(id);

		if (user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable("id") Long id, @RequestBody User user) {

		Optional<User> existingUser = userService.getUserById(id);

		if (existingUser.isPresent()) {
			user.setId(id);
			User updatedUser = userService.saveUser(user);
			return new ResponseEntity<>(updatedUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
		Optional<User> user = userService.getUserById(id);

		if (user.isPresent()) {
			userService.deleteUser(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
