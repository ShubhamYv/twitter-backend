package com.twitter.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.twitter.config.JwtProvider;
import com.twitter.exception.UserException;
import com.twitter.model.User;
import com.twitter.repository.UserRepository;
import com.twitter.service.UserServices;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserById(Long userId) throws UserException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new UserException("User not found with id : " + userId));
		return user;
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email = jwtProvider.getEmailFromToken(jwt);
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserException("User not found with email : " + email);
		}
		return user;
	}

	@Override
	public User updateUser(Long userId, User reqUser) throws UserException {
		User user = findUserById(userId);

		if (reqUser.getFullName() != null) {
			user.setFullName(reqUser.getFullName());
		}

		if (reqUser.getImage() != null) {
			user.setImage(reqUser.getImage());
		}

		if (reqUser.getBackgroundImage() != null) {
			user.setBackgroundImage(reqUser.getBackgroundImage());
		}

		if (reqUser.getBirthDate() != null) {
			user.setBirthDate(reqUser.getBirthDate());
		}

		if (reqUser.getLocation() != null) {
			user.setLocation(reqUser.getLocation());
		}

		if (reqUser.getBio() != null) {
			user.setBio(reqUser.getBio());
		}

		if (reqUser.getWebsite() != null) {
			user.setWebsite(reqUser.getWebsite());
		}

		User savedUser = userRepository.save(user);
		return savedUser;
	}

	@Override
	public User followUser(Long userId, User user) throws UserException {
		User followToUser = findUserById(userId);

		if (user.getFollowings().contains(followToUser) && followToUser.getFollowers().contains(user)) {
			user.getFollowings().remove(followToUser);
			followToUser.getFollowers().remove(user);
		} else {
			user.getFollowings().add(followToUser);
			followToUser.getFollowers().add(user);
		}
		userRepository.save(followToUser);
		userRepository.save(user);
		return followToUser;
	}

	@Override
	public List<User> searchUser(String query) {
		return userRepository.searchUser(query);
	}

}
