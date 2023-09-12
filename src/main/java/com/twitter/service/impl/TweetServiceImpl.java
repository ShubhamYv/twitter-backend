package com.twitter.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.exception.TweetException;
import com.twitter.exception.UserException;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import com.twitter.repository.TweetRepository;
import com.twitter.request.TweetReplyRequest;
import com.twitter.service.TweetServices;

@Service
public class TweetServiceImpl implements TweetServices {

	@Autowired
	private TweetRepository tweetRepository;

	@Override
	public Tweet createTweet(Tweet req, User user) throws UserException {
		Tweet tweet = new Tweet();
		tweet.setContent(req.getContent());
		tweet.setCreatedAt(LocalDateTime.now());
		tweet.setImage(req.getImage());
		tweet.setVideo(req.getVideo());
		tweet.setUser(user);
		tweet.setReply(false);
		tweet.setTweet(true);
		return tweetRepository.save(tweet);
	}

	@Override
	public List<Tweet> findAllTweets() {
		List<Tweet> allTweets = tweetRepository.findAllByIsTweetTrueOrderByCreatedAtDesc();
		return allTweets;
	}

	@Override
	public Tweet retweet(Long tweetId, User user) throws UserException, TweetException {
		Tweet tweet = findById(tweetId);
		if (tweet.getRetweetUser().contains(user)) {
			tweet.getRetweetUser().remove(user);
		}

		else {
			tweet.getRetweetUser().add(user);
		}
		return tweetRepository.save(tweet);
	}

	@Override
	public Tweet findById(Long tweetId) throws TweetException {
		return tweetRepository.findById(tweetId)
				.orElseThrow(() -> new TweetException("Tweet not found with id " + tweetId));
	}

	@Override
	public void deleteTweetById(Long tweetId, Long userId) throws UserException, TweetException {
		Tweet tweet = findById(tweetId);
		if (!userId.equals(tweet.getUser().getId())) {
			throw new UserException("You cannot delete another user's tweet...");
		}
		tweetRepository.deleteById(tweet.getId());
	}

	@Override
	public Tweet removeFromRetweet(Long tweetId, User user) throws UserException, TweetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tweet createReply(TweetReplyRequest req, User user) throws TweetException {
		Tweet replyTweet = new Tweet();
		replyTweet.setContent(req.getContent());
		replyTweet.setCreatedAt(LocalDateTime.now());
		replyTweet.setImage(req.getImage());
		replyTweet.setVideo(req.getVideo());
		replyTweet.setUser(user);
		replyTweet.setReply(true);
		replyTweet.setTweet(false);

		Tweet replyFor = findById(req.getTweetId());
		replyTweet.setReplyFor(replyFor);
		tweetRepository.save(replyFor);

		Tweet savedReply = tweetRepository.save(replyTweet);
		replyFor.getReplyTweets().add(savedReply);
		return replyFor;
	}

	@Override
	public List<Tweet> getUserTweet(User user) {
		return tweetRepository.findByRetweetUserContainsOrUserIdAndIsTweetTrueOrderByCreatedAtDesc(user, user.getId());
	}

	@Override
	public List<Tweet> findByLikesContainsUser(User user) {
		return tweetRepository.findByLikesUserId(user.getId());
	}

}
