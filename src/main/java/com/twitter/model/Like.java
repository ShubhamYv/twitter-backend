package com.twitter.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "LIKES")
public class Like {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "like_id")
	private Long id;
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private Tweet tweet;
}
