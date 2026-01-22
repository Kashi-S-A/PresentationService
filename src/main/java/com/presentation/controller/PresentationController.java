package com.presentation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.presentation.entity.Presentation;
import com.presentation.service.PresentationService;

@RestController
@RequestMapping("/presentation")
public class PresentationController {
	
	 @Autowired
	    private PresentationService presentationService;

	    //assign
	    @PostMapping("/assign/{adminId}/{studentId}")
	    public ResponseEntity<Presentation> assignPresentation(@PathVariable Integer adminId,
	    		@PathVariable Integer studentId, @RequestBody Presentation presentation) {
	        return ResponseEntity.ok(presentationService.assignPresentationToStudent( adminId, studentId, presentation ));
	    }
	    
	    //fetch presentation by id
	    @GetMapping("/{presentationId}")
	    public ResponseEntity<Presentation> getPresentationById(@PathVariable Integer presentationId) {
	        return ResponseEntity.ok(
	                presentationService.getPresentationById(presentationId)
	        );
	    }
	    
	    //get all presentation by student id
	    @GetMapping("/student/{studentId}")
	    public ResponseEntity<List<Presentation>> getPresentationsByStudent(@PathVariable Integer studentId) {
	        return ResponseEntity.ok(presentationService.getPresentationsByStudentId(studentId));
		
	    }

}
