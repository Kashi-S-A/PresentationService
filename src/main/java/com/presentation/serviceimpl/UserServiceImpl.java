package com.presentation.serviceimpl;

import java.util.List;

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
import com.presentation.exception.InvalidOperationException;
import com.presentation.exception.ResourceNotFoundException;
import com.presentation.repository.UserRepository;
import com.presentation.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<String> save(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new InvalidOperationException("User Already Registered");
        }

        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Registered Successfully");
    }

    @Override
    public Boolean login(LoginDTO loginDTO) {

        User user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("Invalid email"));

        return user.getPassword().equals(loginDTO.getPassword());
    }

    @Override
    public List<User> fetchAllStudents() {
        return userRepository.findByRole(Role.STUDENT);
    }

    @Override
    public ResponseEntity<String> updateStatus(Integer userId, Status status) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + userId));

        if (!user.getRole().equals(Role.STUDENT)) {
            throw new InvalidOperationException("User is not a student");
        }

        user.setStatus(status);
        userRepository.save(user);

        return ResponseEntity.ok("Status updated successfully");
    }

    @Override
    public ResponseEntity<UserDTO> getById(Integer id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserDTO dto = new UserDTO();
        BeanUtils.copyProperties(user, dto);

        return ResponseEntity.ok(dto);
    }
}
