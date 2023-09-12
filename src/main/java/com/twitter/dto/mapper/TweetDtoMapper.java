package com.twitter.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.twitter.dto.TweetDto;
import com.twitter.dto.UserDto;
import com.twitter.model.Tweet;
import com.twitter.model.User;
import com.twitter.util.TwitUtil;

public class TweetDtoMapper {

	public static TweetDto toTweetDto(Tweet tweet, User reqUser) {
		UserDto userDto = UserDtoMapper.toUserDto(tweet.getUser());
		boolean isLiked = TwitUtil.isLikedByReqUser(reqUser, tweet);
		boolean isRetweeted = TwitUtil.isRetweetedByReqUser(reqUser, tweet);

		List<Long> retweetUserId = new ArrayList<>();

		for (User user1 : tweet.getRetweetUser()) {
			retweetUserId.add(user1.getId());
		}

		TweetDto tweetDto = new TweetDto();
		tweetDto.setId(tweet.getId());
		tweetDto.setContent(tweet.getContent());
		tweetDto.setCreatedAt(tweet.getCreatedAt());
		tweetDto.setImage(tweet.getImage());
		tweetDto.setTotalLikes(tweet.getLikes().size());
		tweetDto.setTotalReplies(tweet.getReplyTweets().size());
		tweetDto.setTotalRetweets(tweet.getRetweetUser().size());
		tweetDto.setUser(userDto);
		tweetDto.setLiked(isLiked);
		tweetDto.setRetweet(isRetweeted);
		tweetDto.setRetweetUsersId(retweetUserId);
		tweetDto.setReplyTweets(toTwitDtos(tweet.getReplyTweets(), reqUser));
		tweetDto.setVideo(tweet.getVideo());
		return tweetDto;
	}

	public static List<TweetDto> toTwitDtos(List<Tweet> tweets, User reqUser) {
		List<TweetDto> tweetDtos = new ArrayList<>();
		for (Tweet tweet : tweets) {
			TweetDto tweetDto = toReplyTweetDto(tweet, reqUser);
			tweetDtos.add(tweetDto);
		}
		return tweetDtos;
	}

	private static TweetDto toReplyTweetDto(Tweet tweet, User reqUser) {
		UserDto userDto = UserDtoMapper.toUserDto(tweet.getUser());
		boolean isLiked = TwitUtil.isLikedByReqUser(reqUser, tweet);
		boolean isRetweeted = TwitUtil.isRetweetedByReqUser(reqUser, tweet);

		List<Long> retweetUserId = new ArrayList<>();

		for (User user1 : tweet.getRetweetUser()) {
			retweetUserId.add(user1.getId());
		}

		TweetDto tweetDto = new TweetDto();
		tweetDto.setId(tweet.getId());
		tweetDto.setContent(tweet.getContent());
		tweetDto.setCreatedAt(tweet.getCreatedAt());
		tweetDto.setImage(tweet.getImage());
		tweetDto.setTotalLikes(tweet.getLikes().size());
		tweetDto.setTotalReplies(tweet.getReplyTweets().size());
		tweetDto.setTotalRetweets(tweet.getRetweetUser().size());
		tweetDto.setUser(userDto);
		tweetDto.setLiked(isLiked);
		tweetDto.setRetweet(isRetweeted);
		tweetDto.setRetweetUsersId(retweetUserId);
		tweetDto.setVideo(tweet.getVideo());
		return tweetDto;
	}
}
