package com.presentation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.presentation.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);

	boolean findByEmailAndPassword(String email, String password);
}
