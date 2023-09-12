package com.twitter.service;

import java.util.List;

import com.twitter.exception.UserException;
import com.twitter.model.User;

public interface UserServices {

	User findUserById(Long userId) throws UserException;

	User findUserProfileByJwt(String jwt) throws UserException;

	User updateUser(Long userId, User user) throws UserException;

	User followUser(Long userId, User user) throws UserException;

	List<User> searchUser(String query);
}
