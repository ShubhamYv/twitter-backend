package com.twitter.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.twitter.dto.UserDto;
import com.twitter.model.User;

public class UserDtoMapper {

	public static UserDto toUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setBackgroundImage(user.getBackgroundImage());
		userDto.setBio(user.getBio());
		userDto.setBirthDate(user.getBirthDate());
		userDto.setEmail(user.getEmail());
		userDto.setFullName(user.getFullName());
		userDto.setImage(user.getImage());
		userDto.setFollowers(toUserDto(user.getFollowers()));
		userDto.setFollowing(toUserDto(user.getFollowings()));
		userDto.setLoginWithGoogle(user.isLoginWithGoogle());
		userDto.setLocation(user.getLocation());
//		userDto.setVerified(false);
		return userDto;
	}

	private static List<UserDto> toUserDto(List<User> followers) {
		List<UserDto> userDtos = new ArrayList<>();
		for (User user : followers) {
			UserDto userDto = new UserDto();
			userDto.setId(user.getId());
			userDto.setEmail(user.getEmail());
			userDto.setFullName(user.getFullName());
			userDto.setImage(user.getImage());
			userDtos.add(userDto);
		}
		return userDtos;
	}
}
