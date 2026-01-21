package com.presentation.serviceimpl;

import com.presentation.entity.Presentation;
import com.presentation.entity.Rating;
import com.presentation.entity.User;
import com.presentation.repository.PresentationRepo;
import com.presentation.repository.RatingRepo;
import com.presentation.repository.UserRepository;
import com.presentation.service.RatingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

	@Autowired
	private RatingRepo ratingRepo;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PresentationRepo presentationRepo;

	@Override
	public Rating createRating(Rating rating) {

		User user = userRepository.findByEmail(rating.getUser().getEmail())
				.orElseThrow(() -> new RuntimeException("User not found with email"));

		Presentation presentation = presentationRepo.findById(rating.getPresentation().getPid())
				.orElseThrow(() -> new RuntimeException("Presentation not found"));

		double totalScore = rating.getCommunication() + rating.getConfidence() + rating.getContent()
				+ rating.getInteraction() + rating.getLiveliness() + rating.getUsageProps();

		rating.setTotalScore(totalScore);
		rating.setUser(user);
		rating.setPresentation(presentation);

		return ratingRepo.save(rating);
	}

	@Override
	public Rating getRatingById(Integer id) {
		return ratingRepo.findById(id).orElse(null);
	}

	@Override
	public Page<Rating> getAllRatings(int page, int size, String sortBy, String direction) {

		Sort sort = "desc".equalsIgnoreCase(direction) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		return ratingRepo.findAll(pageable);
	}

	@Override
	public List<Rating> getRatingsByPresentation(Integer presentationId) {

		presentationRepo.findById(presentationId)
				.orElseThrow(() -> new RuntimeException("Presentation not found with id: " + presentationId));

		return ratingRepo.findByPresentationPid(presentationId);
	}

	@Override
	public Page<Rating> getRatingsByUser(String email, int page, int size, String sortBy, String direction) {

		userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with email: " + email));

		Sort sort = "desc".equalsIgnoreCase(direction) ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);

		return ratingRepo.findByUserEmail(email, pageable);
	}

}