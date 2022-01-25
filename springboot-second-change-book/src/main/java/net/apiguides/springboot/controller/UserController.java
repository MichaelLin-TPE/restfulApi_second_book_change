package net.apiguides.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;

import net.apiguides.springboot.entity.User;
import net.apiguides.springboot.exception.ResourceNotFoundException;
import net.apiguides.springboot.repository.UserRepository;

@RestController
@RequestMapping("/api/users")
public class UserController {

	
	
	@Autowired
	private UserRepository userRepository;
	
	//get all users ;
	@GetMapping
	public List<User> getAllUsers(){
		
		//get all users
		
		return userRepository.findAll();
		
	}
	
	//get user by id
	@GetMapping("/{id}")
	public User getUserById(@PathVariable (value = "id") long userId) {
		
		return this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found ID"));
	}
	//create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return this.userRepository.save(user);
	}
	//update user
	@PutMapping("/{id}")
	public User updateUser(@RequestBody User user , @PathVariable(value = "id")long userId) {
		
		User oldUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found ID"));
		oldUser.setEmail(user.getEmail());
		oldUser.setFirstName(user.getFirstName());
		oldUser.setLastName(user.getLastName());
		
		return this.userRepository.save(oldUser);
	}
	
	
	//delete user by id
	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id")long userId){
		
		User oldUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found ID"));
		this.userRepository.delete(oldUser);
		return ResponseEntity.ok().build();
	}

	
	
}
