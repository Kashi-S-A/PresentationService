package com.presentation.service;

import java.util.List;

import com.presentation.entity.Presentation;

public interface PresentationService {

	public Presentation assignPresentationToStudent(Integer adminId, Integer studentId,Presentation presentation);
	
	 Presentation getPresentationById(Integer presentationId);
	 
	List<Presentation> getPresentationsByStudentId(Integer studentId);
	

}
