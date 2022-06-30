package com.inkathon.OnlineBedAvailabilityTracker.controllers;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.inkathon.OnlineBedAvailabilityTracker.dto.ResponseDto;
import com.inkathon.OnlineBedAvailabilityTracker.dto.UserDto;
import com.inkathon.OnlineBedAvailabilityTracker.services.UserService;

@RestController
@RequestMapping(path ={ "/api/login", "/api/signup"})
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseDto getUserInfo(@RequestParam(required = true) String username,
			@RequestParam(required = true) String password) throws NoSuchAlgorithmException {
		return userService.getUserInfo(username, password);
	}

	@PostMapping
	public ResponseDto registerNewUser(@RequestBody UserDto userdto) {
		return userService.addNewUser(userdto);
	}

	@DeleteMapping(path = "/{userId}")
	public ResponseDto deleteUser(@PathVariable("userId") Long userId) {
		return userService.deleteUser(userId);
	}

	@PutMapping
	public ResponseDto updateUser(@RequestBody UserDto userdto) {
		return userService.updateUser(userdto);
	}

}
