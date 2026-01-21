package com.presentation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Rating {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer communication;
	private Integer confidence;
	private Integer content;
	private Integer interaction;
	private Integer liveliness;
	private Integer usageProps;
	private Double totalScore;

	@ManyToOne
	private User user;

	@OneToOne
	private Presentation presentation;
}
