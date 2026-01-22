package com.presentation.service;

import com.presentation.entity.Presentation;
import com.presentation.enums.PresentationStatus;

public interface PresentationService {

	Presentation changeStatus(Integer pid, PresentationStatus status);

	Presentation updateUserTotalScore(Integer pid, Double userTotalScore);

	public Presentation assignPresentationToStudent(Integer adminId, Integer studentId,Presentation presentation);
	
	 Presentation getPresentationById(Integer presentationId);
	 
	List<Presentation> getPresentationsByStudentId(Integer studentId);
	

}
