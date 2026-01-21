package com.presentation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.presentation.entity.Presentation;

public interface PresentationRepo extends JpaRepository<Presentation, Integer> {

}
