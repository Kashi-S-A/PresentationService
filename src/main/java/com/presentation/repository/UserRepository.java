package com.presentation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.presentation.entity.User;
import com.presentation.enums.Role;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	boolean findByEmailAndPassword(String email, String password);
		
	Optional<User> findByIdAndRole(Integer id, Role role);
}
