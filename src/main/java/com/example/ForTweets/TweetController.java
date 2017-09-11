package com.example.ForTweets;

import com.example.ForStatus.ErrorMessage;

import com.example.ForUsers.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * Created by shantanu on 10/8/16.
 */

@RestController
public class TweetController {

    @Autowired
    TweetService tweetService;

    @Autowired
    UserRepository userRepository;


    //-------------------------------------------------------------------------------------------------//


    @RequestMapping(value="/tweetInsert" , method = RequestMethod.POST)
    ResponseEntity<?> tweetInsert(@RequestBody String hello) {

        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String email=auth.getName();

        Tweets  obj =new Tweets();


        obj.setContent(hello);
        obj.setTweetDate(new Date());
        obj.setUsername(email);

        tweetService.tweetInsert(obj);
        return new ResponseEntity<>(new ErrorMessage("Tweeted!"),HttpStatus.OK);


    }




    @RequestMapping("/tweetDisplay")
    public List<Tweets> tweetDisplay() {
        return tweetService.tweetDisplay();
    }
}

