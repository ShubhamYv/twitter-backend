package com.twitter.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserDto {

	private Long id;
	private String fullName;
	private String location;
	private String website;
	private String birthDate;
	private String email;
	private String password;
	private String mobile;
	private String image;
	private String backgroundImage;
	private String bio;
	private boolean reqUser;
	private boolean loginWithGoogle;

	private List<UserDto> followers = new ArrayList<>();
	private List<UserDto> following = new ArrayList<>();

	private boolean isFollowed;

	private boolean isVerified;
}
