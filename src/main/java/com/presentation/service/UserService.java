package com.presentation.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.presentation.dto.LoginDTO;
import com.presentation.dto.UserDTO;
import com.presentation.entity.User;
import com.presentation.enums.Status;

public interface UserService {

	ResponseEntity<String> save(User user);

	Boolean login(LoginDTO loginDTO);

	public List<User> fetchAllStudents();

	ResponseEntity<String> updateStatus(Integer userId, Status status);

	ResponseEntity<UserDTO> getById(Integer id);

}
