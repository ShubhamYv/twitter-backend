package com.twitter.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.twitter.dto.LikeDto;
import com.twitter.dto.TweetDto;
import com.twitter.dto.UserDto;
import com.twitter.model.Like;
import com.twitter.model.User;

public class LikeDtoMapper {

	public static LikeDto toLikeDto(Like like, User reqUser) {

		UserDto userDto = UserDtoMapper.toUserDto(like.getUser());
		UserDto reqUserDto = UserDtoMapper.toUserDto(reqUser);
		TweetDto tweetDto = TweetDtoMapper.toTweetDto(like.getTweet(), reqUser);

		LikeDto likeDto = new LikeDto();
		likeDto.setId(like.getId());
		likeDto.setTweet(tweetDto);
		likeDto.setUser(userDto);

		return likeDto;
	}

	public static List<LikeDto> toLikeDtos(List<Like> likes, User reqUser) {
		List<LikeDto> likeDtos = new ArrayList<>();

		for (Like like : likes) {
			UserDto userDto = UserDtoMapper.toUserDto(like.getUser());
			TweetDto tweetDto = TweetDtoMapper.toTweetDto(like.getTweet(), reqUser);

			LikeDto likeDto = new LikeDto();
			likeDto.setId(like.getId());
			likeDto.setTweet(tweetDto);
			likeDto.setUser(userDto);
			likeDtos.add(likeDto);
		}
		return likeDtos;
	}
}
