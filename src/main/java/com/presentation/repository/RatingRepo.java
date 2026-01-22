package com.presentation.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.presentation.entity.Rating;

public interface RatingRepo extends JpaRepository<Rating, Integer> {

	List<Rating> findByPresentationPid(Integer pid);

	Page<Rating> findByUserEmail(String email, Pageable pageable);

}
