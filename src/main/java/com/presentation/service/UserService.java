package com.presentation.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import com.presentation.dto.LoginDTO;
import com.presentation.entity.User;

public interface UserService {

	ResponseEntity<String> save(User user);

	Boolean login(LoginDTO loginDTO);
	
//	User getUserByEmail(String email);
	public List<User> fetchAll();

	void updateStatus(Long userId);

//	void updateStatus(Long id);

}
