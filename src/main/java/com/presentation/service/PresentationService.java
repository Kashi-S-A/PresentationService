package com.presentation.service;

import java.util.List;

import com.presentation.entity.Presentation;
import com.presentation.enums.PresentationStatus;

public interface PresentationService {

	Presentation changeStatus(Integer pid, PresentationStatus status);

	public Presentation assignPresentationToStudent(Integer adminId, Integer studentId, Presentation presentation);

	Presentation getPresentationById(Integer presentationId);

	List<Presentation> getPresentationsByStudentId(Integer studentId);

}
