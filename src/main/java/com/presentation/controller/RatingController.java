
package com.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presentation.entity.Rating;
import com.presentation.service.RatingService;

@RestController
@RequestMapping("/rate")
public class RatingController {

	@Autowired
	private RatingService ratingService;

	// GET /rate/presentation/5
	@GetMapping("/presentation/{presentationId}")
	public ResponseEntity<?> getRatingsByPresentation(@PathVariable Integer presentationId) {
		Rating rating = ratingService.getRatingsByPresentation(presentationId);

		if (rating != null) {
			return ResponseEntity.status(HttpStatus.OK).body(rating);
		}

		return ResponseEntity.badRequest().body("Rating is not yet given");
	}

}
