package com.presentation.service;

import com.presentation.entity.Presentation;
import com.presentation.enums.PresentationStatus;

public interface PresentationService {

	Presentation changeStatus(Integer pid, PresentationStatus status);

	Presentation updateUserTotalScore(Integer pid, Double userTotalScore);
	

}
