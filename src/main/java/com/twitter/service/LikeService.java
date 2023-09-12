package com.twitter.service;

import java.util.List;

import com.twitter.exception.TweetException;
import com.twitter.exception.UserException;
import com.twitter.model.Like;
import com.twitter.model.User;

public interface LikeService {

	Like likeTweets(Long tweetId, User user) throws UserException, TweetException;

	List<Like> getAllLikes(Long tweetid) throws TweetException;
	
	

}
