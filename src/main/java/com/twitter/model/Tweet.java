package com.twitter.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Tweet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tweet_id")
	private Long id;

	@ManyToOne
	private User user;
	private String content;
	private String image;
	private String video;

	@OneToMany(mappedBy = "tweet", cascade = CascadeType.ALL)
	private List<Like> likes = new ArrayList<>();

	@OneToMany
	private List<Like> replyTweets = new ArrayList<>();

	@ManyToMany
	private List<User> retweet = new ArrayList<>();

	@ManyToOne
	private Tweet replyFor;

	private boolean isReply;

	private boolean isTweet;
}
