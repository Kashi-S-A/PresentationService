package com.presentation.dto;

import com.presentation.enums.Role;
import com.presentation.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private String name;
	private String email;
	private Long phone;
	private Status status;
	private Role role;
	private Double userTotalScore;
}
