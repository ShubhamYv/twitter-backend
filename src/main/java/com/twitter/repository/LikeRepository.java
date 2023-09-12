package com.twitter.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.twitter.model.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {

	@Query("SELECT l FROM Like l WHERE l.user.id= :userId AND l.tweet.id= :tweetId")
	Like isLikeExist(@Param("userId") Long userId, @Param("tweetId") Long tweetId);

	@Query("SELECT l FROM Like l WHERE l.tweet.id= :tweetId")
	List<Like> findByTweetId(@Param("tweetId") long tweetId);
}
