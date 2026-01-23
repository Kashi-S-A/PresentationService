package com.presentation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.presentation.enums.PresentationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Presentation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer pid;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private User user;
	
	private String course;
	private String topic;

	@Enumerated(EnumType.STRING)
	private PresentationStatus presentationStatus = PresentationStatus.ASSIGNED;

	private Double presentationTotalScore;

	@OneToOne
	@JoinColumn(name = "rating_id")
	private Rating rating;
}
