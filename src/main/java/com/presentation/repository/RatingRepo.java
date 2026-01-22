package com.presentation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.presentation.entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, Integer> {

	Optional<Rating> findByPresentationPid(Integer pid);

}
