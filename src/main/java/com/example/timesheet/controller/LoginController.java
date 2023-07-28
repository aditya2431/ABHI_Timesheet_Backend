package com.example.timesheet.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.timesheet.exception.ResourceNotFoundException;
import com.example.timesheet.model.Timesheet;
import com.example.timesheet.model.User;
import com.example.timesheet.repository.UserRepository;

@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/login")
	public List<User> getAllRecords() {
		return userRepository.findAll();
	}

	@PostMapping("/register")
	public User createRecord(@Valid @RequestBody User user) {
		return userRepository.save(user);
	}

	@GetMapping("/login/{id}")
	public User getRecordById(@PathVariable(value = "id") String userName) {
		return userRepository.findById(userName)
				.orElseThrow(() -> new ResourceNotFoundException("User", "id", userName));
	}

	@GetMapping("/validateLogin")
	public boolean getByUserNamePassword(@RequestParam String userName,@RequestParam String password) {
		boolean result = false;
		List<User> users = userRepository.findByUserNameAndPassword(userName, password);
		if (users.size() > 0) {
			result =  true;
		}
		return result;
	}
	
	@GetMapping("/findByAbhiManager/{id}")
	public List<User> getRecordByAbhiManager(@PathVariable(value = "id") String abhiManager) {
		return userRepository.findByabhiManager(abhiManager);
	}
	
	@GetMapping("/findByPartnerManager/{id}")
	public List<User> findByPartnerManager(@PathVariable(value = "id") String partnerManager) {
		return userRepository.findByPartnerManager(partnerManager);
	}

}
