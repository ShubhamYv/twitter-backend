package com.twitter.service;

import java.util.List;

import com.twitter.exception.TweetException;
import com.twitter.exception.UserException;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import com.twitter.request.TweetReplyRequest;

public interface TweetServices {

	Tweet createTweet(Tweet req, User user) throws UserException;

	List<Tweet> findAllTweets();

	Tweet retweet(Long tweetId, User user) throws UserException, TweetException;

	Tweet findById(Long tweetId) throws TweetException;

	void deleteTweetById(Long tweetId, Long userId) throws UserException, TweetException;

	Tweet removeFromRetweet(Long tweetId, User user) throws UserException, TweetException;

	Tweet createReply(TweetReplyRequest req, User user) throws TweetException;

	List<Tweet> getUserTweet(User user);

	List<Tweet> findByLikesContainsUser(User user);
}
