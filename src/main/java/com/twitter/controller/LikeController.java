package com.twitter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.dto.LikeDto;
import com.twitter.dto.mapper.LikeDtoMapper;
import com.twitter.exception.TweetException;
import com.twitter.exception.UserException;
import com.twitter.model.Like;
import com.twitter.model.User;
import com.twitter.service.LikeService;
import com.twitter.service.UserServices;

@RestController
@RequestMapping("/api")
public class LikeController {

	@Autowired
	private UserServices userService;

	@Autowired
	private LikeService likeService;

	@PostMapping("/{tweetId}/likes")
	public ResponseEntity<LikeDto> likeTweet(@PathVariable Long tweetId, @RequestHeader("Authorization") String jwt)
			throws UserException, TweetException {

		User user = userService.findUserProfileByJwt(jwt);
		Like like = likeService.likeTweets(tweetId, user);

		LikeDto likeDto = LikeDtoMapper.toLikeDto(like, user);
		return new ResponseEntity<LikeDto>(likeDto, HttpStatus.CREATED);
	}

	@PostMapping("/tweet/{tweetId}")
	public ResponseEntity<List<LikeDto>> getAllLikes(@PathVariable Long tweetId,
			@RequestHeader("Authorization") String jwt) throws UserException, TweetException {
		User user = userService.findUserProfileByJwt(jwt);
		List<Like> like = likeService.getAllLikes(tweetId);
		List<LikeDto> likeDto = LikeDtoMapper.toLikeDtos(like, user);
		return new ResponseEntity<List<LikeDto>>(likeDto, HttpStatus.CREATED);
	}
}
