package com.presentation.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.presentation.entity.Presentation;
import com.presentation.enums.PresentationStatus;
import com.presentation.repository.PresentationRepo;
import com.presentation.service.PresentationService;

@Service
public class PresentationServiceImpl implements PresentationService {

	@Autowired
	private PresentationRepo presentationRepo;

	@Override
	public Presentation changeStatus(Integer pid,
	                                 PresentationStatus status) {

		Presentation presentation = presentationRepo.findById(pid)
				.orElseThrow(() -> new RuntimeException("Presentation not found"));

		presentation.setPresentationStatus(status);

		return presentationRepo.save(presentation);
	}

	@Override
	public Presentation updateUserTotalScore(Integer pid,
	                                         Double userTotalScore) {

		Presentation presentation = presentationRepo.findById(pid)
				.orElseThrow(() -> new RuntimeException("Presentation not found"));

		presentation.setUserTotalScore(userTotalScore);
		presentation.setPresentationStatus(PresentationStatus.COMPLETED);

		return presentationRepo.save(presentation);
	}
}
