package com.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.presentation.entity.Presentation;
import com.presentation.enums.PresentationStatus;
import com.presentation.service.PresentationService;

//both Admin and student
@RestController
@RequestMapping("/presentation")
public class PresentationController {

	@Autowired
	private PresentationService presentationService;

	@PutMapping("/change-status/{id}")
	public ResponseEntity<Presentation> changeStatus(@PathVariable Integer id,
			@RequestParam PresentationStatus status) {

		return ResponseEntity.ok(presentationService.changeStatus(id, status));
	}

	// fetch presentation by id
	@GetMapping("/{presentationId}")
	public ResponseEntity<Presentation> getPresentationById(@PathVariable Integer presentationId) {
		return ResponseEntity.ok(presentationService.getPresentationById(presentationId));
	}

	// get all presentation by student id
	@GetMapping("/student/{studentId}")
	public ResponseEntity<List<Presentation>> getPresentationsByStudent(@PathVariable Integer studentId) {
		return ResponseEntity.ok(presentationService.getPresentationsByStudentId(studentId));

	}

}
