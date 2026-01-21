package com.presentation.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.presentation.dto.LoginDTO;
import com.presentation.entity.User;
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
		            .orElseThrow(() ->
		                    new RuntimeException("Invalid email"));

		    return user.getPassword().equals(loginDTO.getPassword());
	}

	@Override
	public List<User> fetchAll() {
		
		return userRepository.findAll();
	}

	@Override
	public void updateStatus(Long userId) {
		
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new RuntimeException("User not found with id:"+ userId));
		
		if(user.getStatus() == Status.ACTIVE) {
			user.setStatus(Status.INACTIVE);
		}else {
			user.setStatus(Status.ACTIVE);
		}
		
		userRepository.save(user);
		
		
	}


}
