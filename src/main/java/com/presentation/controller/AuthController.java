package com.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.presentation.dto.LoginDTO;
import com.presentation.dto.UserDTO;
import com.presentation.entity.User;
import com.presentation.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	//GET USER BY ID
	@GetMapping("/user")
	public ResponseEntity<UserDTO> getById(@RequestParam Integer id) {
		return userService.getById(id);
	}
	

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody User user) {
		return userService.save(user);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
		Boolean login = userService.login(loginDTO);
		if (login) {
			return new ResponseEntity<String>("Logged In Successful", HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Credentials");
	}

}
