package com.example.ForTweets;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shantanu on 10/8/16.
 */

public interface TweetsRepository extends JpaRepository<Tweets, Integer> {

    List<Tweets> findByTweetId(int tweedId);
   List<Tweets> findByUsername(String username);



}
