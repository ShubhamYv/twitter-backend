package com.twitter.dto;

import lombok.Data;

@Data
public class LikeDto {

	private Long id;
	private UserDto user;
	private TweetDto tweet;
}
