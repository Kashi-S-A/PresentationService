package com.presentation.service;

import com.presentation.entity.Rating;

public interface RatingService {
	Rating createRating(Integer presentationId, Rating rating);

	Rating getRatingsByPresentation(Integer presentationId);

}
