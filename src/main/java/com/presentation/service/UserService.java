package com.presentation.service;

import org.springframework.http.ResponseEntity;

import com.presentation.dto.LoginDTO;
import com.presentation.entity.User;

public interface UserService {

	ResponseEntity<String> save(User user);

	Boolean login(LoginDTO loginDTO);

}
