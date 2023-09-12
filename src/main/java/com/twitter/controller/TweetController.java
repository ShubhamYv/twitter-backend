package com.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.twitter.service.TweetServices;
import com.twitter.service.UserServices;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

	@Autowired
	private TweetServices tweetService;
	
	@Autowired
	private UserServices userService;
}
