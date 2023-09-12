package com.twitter.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class TweetDto {
	private Long id;
	private UserDto user;
	private String content;
	private String image;
	private String video;
	private LocalDateTime createdAt;
	private Integer totalLikes;
	private Integer totalReplies;
	private Integer totalRetweets;
	private boolean isLiked;
	private boolean isRetweet;

	private List<Long> retweetUsersId;
	private List<TweetDto> replyTweets;
}
