package com.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presentation.entity.Presentation;
import com.presentation.entity.Rating;
import com.presentation.entity.User;
import com.presentation.enums.Status;
import com.presentation.service.PresentationService;
import com.presentation.service.RatingService;
import com.presentation.service.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private RatingService ratingService;

	@Autowired
	private PresentationService presentationService;

	// assign
	@PostMapping("/assign/{adminId}/{studentId}")
	public ResponseEntity<Presentation> assignPresentation(@PathVariable Integer adminId,
			@PathVariable Integer studentId, @RequestBody Presentation presentation) {
		return ResponseEntity.ok(presentationService.assignPresentationToStudent(adminId, studentId, presentation));
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.fetchAllStudents();
	}

	@PutMapping("/users/{id}/{status}")
	public ResponseEntity<String> toggleUserStatus(@PathVariable Integer id, @PathVariable Status status) {
		return userService.updateStatus(id, status);
	}

	@PostMapping("/{presentationId}")
	public ResponseEntity<Rating> createRating(@PathVariable Integer presentationId, @RequestBody Rating rating) {
		return ResponseEntity.ok(ratingService.createRating(presentationId, rating));
	}

}
