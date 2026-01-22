package com.presentation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	private Integer communication;// out of 10
	private Integer confidence;
	private Integer content;
	private Integer interaction;
	private Integer liveliness;
	private Integer usageProps;

	@OneToOne(mappedBy = "rating")
	@JsonIgnore
	private Presentation presentation;
}
