package com.example.ForFollows;

import com.example.ForStatus.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.http.FormLoginBeanDefinitionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by shantanu on 10/8/16.
 */

@RestController
public class FollowController {

    @Autowired
    FollowServices followServices;

    @Autowired
    FollowRepository followRepository;

    @RequestMapping(value="/tofollow" ,  method = RequestMethod.POST)
    ResponseEntity<?> followInsert(@RequestBody Follows table) {
        System.out.println("check tofollow  function line 1");

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        System.out.println("authentication name - -" +name);

        String UserFollowing = table.getFollowing();

        System.out.println("the person i want to follow--" + UserFollowing);

        table.setFollower(name);
        System.out.println(" setting authenticator as follower - " + table.getFollower());

        List<Follows> follo = followRepository.findByFollower(name);
        System.out.println("size of follow    ---  "+ follo.size());
        for(int j =0; j<follo.size();j++) {
            System.out.println("this is following list --"+follo.get(j).getFollowing());
        }
        if (follo.size() > 0) {

            System.out.println("inside if stmnt ");

            for(int i = 0; i < follo.size(); i++)
                if(follo.get(i).getFollowing().equals(table.getFollowing())){
                    System.out.println("inside loop to check -- ");
                    return new ResponseEntity<>(new ErrorMessage(" Already Following "), HttpStatus.BAD_REQUEST);
                     }
            System.out.println("out of if ");
        }


            System.out.println("inside else statement that return positive response ");
            followServices.FollowInsert(table);

            return new ResponseEntity<>(new ErrorMessage(" Happy Following  "), HttpStatus.OK);


    }
//   =========== The person which I follow  =====================================

    @RequestMapping("/findfollowing")
    List<Follows>findFollowing() {


     Authentication auth= SecurityContextHolder.getContext().getAuthentication();
      String name=auth.getName();
        System.out.println(name);
     return followServices.myFollowing(name);

      }

// =================the person that follow me ==============================//


    @RequestMapping("/getfollower")
    List<Follows>getFollowing() {


        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        String name=auth.getName();
        System.out.println(name);

        return followServices.myFollower(name);


    }



}

