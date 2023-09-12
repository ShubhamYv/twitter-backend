package com.twitter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.exception.TweetException;
import com.twitter.exception.UserException;
import com.twitter.model.Like;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import com.twitter.repository.LikeRepository;
import com.twitter.repository.TweetRepository;
import com.twitter.service.LikeService;
import com.twitter.service.TweetServices;

@Service
public class LikeServiceImpl implements LikeService {

	@Autowired
	private LikeRepository likeRepository;

	@Autowired
	private TweetRepository tweetRepository;

	@Autowired
	private TweetServices tweetServices;

	@Override
	public Like likeTweets(Long tweetId, User user) throws UserException, TweetException {
		Like isLikeExist = likeRepository.isLikeExist(user.getId(), tweetId);

		if (isLikeExist != null) {
			likeRepository.deleteById(isLikeExist.getId());
			return isLikeExist;
		}

		Tweet tweet = tweetServices.findById(tweetId);

		Like like = new Like();
		like.setTweet(tweet);
		like.setUser(user);
		Like savedLike = likeRepository.save(like);

		tweet.getLikes().add(savedLike);

		return savedLike;
	}

	@Override
	public List<Like> getAllLikes(Long tweetid) throws TweetException {
//		Tweet tweet = tweetServices.findById(tweetid);
		List<Like> likes = likeRepository.findByTweetId(tweetid);
		return likes;
	}

}
