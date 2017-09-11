package com.example.ForTweets;

import com.example.ForFollows.FollowController;
import com.example.ForFollows.FollowRepository;
import com.example.ForFollows.Follows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shantanu on 10/8/16.
 */


@Service
public class TweetService {

    @Autowired
    TweetsRepository tweetsRepository;

    @Autowired
    FollowRepository followRepository;

    @Transactional
    public List<Tweets> findByTweetId(int tweetId) {
        return tweetsRepository.findByTweetId(tweetId);
    }

    @Transactional
        public List<Tweets> tweetDisplay() {


        System.out.println("***************************initialize display tweet************************* ");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String authName = auth.getName();

        System.out.println(authName);


        List<Tweets> tweetses = new ArrayList<Tweets>();

        List<Follows> followses = followRepository.findByFollower(authName);

        Iterator<Follows> f = followses.iterator();
        Follows x = null;
        String s = null;
        for (int i = 0; i < followses.size(); i++) {
            x = f.next();
            s = x.getFollowing();
            System.out.println("hello !!!!!");
            System.out.println(s);
            List<Tweets> twitter = tweetsRepository.findByUsername(s);
            tweetses.addAll(twitter);
        }

        System.out.println(tweetses);
        return tweetses;
    }


    @Transactional
    public boolean tweetInsert(Tweets xyz) {
        if (tweetsRepository.save(xyz) != null) {
            return true;
        } else
            return false;
    }

}