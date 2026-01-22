package com.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.presentation.entity.Presentation;
import com.presentation.enums.PresentationStatus;
import com.presentation.service.PresentationService;

@RestController
@RequestMapping("/presentation")
public class PresentationController {
	
	@Autowired
	private PresentationService presentationService;

	@PutMapping("/change-status/{id}")
	public ResponseEntity<Presentation> changeStatus(
			@PathVariable Integer id,
			@RequestParam PresentationStatus status) {

		return ResponseEntity.ok(
				presentationService.changeStatus(id, status));
	}

	@PutMapping("/update-total-score/{id}")
	public ResponseEntity<Presentation> updateTotalScore(
			@PathVariable Integer id,
			@RequestParam Double totalScore) {

		return ResponseEntity.ok(
				presentationService.updateUserTotalScore(id, totalScore));
	}

}
