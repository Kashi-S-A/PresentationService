package com.presentation.service;

import com.presentation.entity.Rating;
import java.util.List;

import org.springframework.data.domain.Page;

public interface RatingService {
	Rating createRating(Rating rating);

	Rating getRatingById(Integer id);

	Page<Rating> getAllRatings(int page, int size, String sortBy, String direction);

	List<Rating> getRatingsByPresentation(Integer presentationId);

	Page<Rating> getRatingsByUser(String email, int page, int size, String sortBy, String direction);
}
