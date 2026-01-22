package com.presentation.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.presentation.dto.LoginDTO;
import com.presentation.dto.UserDTO;
import com.presentation.entity.User;
import com.presentation.enums.Role;
import com.presentation.enums.Status;
import com.presentation.repository.UserRepository;
import com.presentation.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public ResponseEntity<String> save(User user) {

		Optional<User> opt = userRepository.findByEmail(user.getEmail());

		if (opt.isPresent()) {
			return new ResponseEntity<String>("Already Registered", HttpStatus.BAD_REQUEST);
		}
		userRepository.save(user);
		return new ResponseEntity<String>("Registered Successfully", HttpStatus.CREATED);
	}

	@Override
	public Boolean login(LoginDTO loginDTO) {
//		return userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
		User user = userRepository.findByEmail(loginDTO.getEmail())
				.orElseThrow(() -> new RuntimeException("Invalid email"));

		return user.getPassword().equals(loginDTO.getPassword());
	}

	@Override
	public List<User> fetchAllStudents() {
		return userRepository.findByRole(Role.STUDENT);
	}

	@Override
	public ResponseEntity<String> updateStatus(Integer userId, Status status) {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id:" + userId));

		if (user.getRole().equals(Role.STUDENT) && status != null) {
			user.setStatus(status);
			userRepository.save(user);
			return ResponseEntity.status(HttpStatus.OK).body("Status updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User is not a student");
		}

	}

	@Override
	public ResponseEntity<UserDTO> getById(Integer id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));

		UserDTO dto = new UserDTO();

		BeanUtils.copyProperties(user, dto);

		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

}
