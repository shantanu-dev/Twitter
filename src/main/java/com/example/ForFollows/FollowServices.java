package com.example.ForFollows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by shantanu on 10/8/16.
 */

@Service
public class FollowServices {


    @Autowired
    FollowRepository followRepository;

    @Transactional
    public boolean FollowInsert(Follows s) {
        if(followRepository.save(s) != null) {
            return true;
        }
        else
            return false;
    }


    @Transactional
    public List<Follows>myFollower(String following){
        return followRepository.findByFollowing(following);
    }


    @Transactional
    public List<Follows>myFollowing(String follower){
        return followRepository.findByFollower(follower);
    }

}
