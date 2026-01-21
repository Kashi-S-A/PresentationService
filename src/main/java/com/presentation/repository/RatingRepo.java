package com.presentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.presentation.entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, Integer> {

}
