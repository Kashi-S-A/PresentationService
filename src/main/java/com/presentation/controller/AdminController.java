package com.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presentation.entity.User;
import com.presentation.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	@Autowired
	private UserService userService;
	
//	 @GetMapping("/users")
//	    public ResponseEntity<List<User>> getAllUsers() {
//		 
//		 userService.getUserByEmail(null)
//	        return ResponseEntity.ok(adminService.getAllUsers());
//	    }
	
	@GetMapping("/fetchUser")
	public List<User> getMethodName() {
		return userService.fetchAll();
	}
	
	@PutMapping("/user/{id}/status")
	public String toggleUserStatus(@PathVariable Long id) {
		userService.updateStatus(id);
		return "User status updated successfully";
	}
	
}
