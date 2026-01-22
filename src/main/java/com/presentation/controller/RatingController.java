
package com.presentation.controller;

import com.presentation.entity.Rating;
import com.presentation.service.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rate")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	@PostMapping
	public ResponseEntity<Rating> createRating(@RequestBody Rating rating) {
		return ResponseEntity.ok(ratingService.createRating(rating));
	}

	// GET /rate/10
	@GetMapping("/{id}")
	public ResponseEntity<Rating> getRatingById(@PathVariable Integer id) {
		Rating rating = ratingService.getRatingById(id);
		if (rating == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(rating);
	}

	// GET /rate?page=0&size=10&sortBy=totalScore&direction=desc
	@GetMapping
	public ResponseEntity<Page<Rating>> getAllRatings(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "id") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {

		return ResponseEntity.ok(ratingService.getAllRatings(page, size, sortBy, direction));
	}

	// GET /rate/presentation/5
	@GetMapping("/presentation/{presentationId}")
	public ResponseEntity<List<Rating>> getRatingsByPresentation(@PathVariable Integer presentationId) {
		return ResponseEntity.ok(ratingService.getRatingsByPresentation(presentationId));
	}

	
	//GET /rate/user?email=student@gmail.com&page=0&size=5&sortBy=totalScore&direction=desc
	@GetMapping("/user")
	public ResponseEntity<Page<Rating>> getRatingsByUser(@RequestParam String email,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size,
			@RequestParam(defaultValue = "totalScore") String sortBy,
			@RequestParam(defaultValue = "desc") String direction) {

		return ResponseEntity.ok(ratingService.getRatingsByUser(email, page, size, sortBy, direction));
	}
}
