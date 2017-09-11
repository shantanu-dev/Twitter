package com.example.ForUsers;

/**
 * Created by shantanu on 10/8/16.
 */

import com.example.ForStatus.ErrorMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Created by shantanu on 10/8/16.
 */

@RestController
public class UserController  {

    @Autowired
    UserService userService;


    @Autowired
    UserRepository userRepository;


    @RequestMapping(value="/Insert" ,  method = RequestMethod.POST)
    ResponseEntity<?> insert(@RequestBody Users hello) {
        String mail = hello.getEmail();

        System.out.println(mail);

        List<Users> email = userRepository.findByEmail(mail);

        if (email.size() > 0) {
            System.out.println("error message must come ");
            return new ResponseEntity<>(new ErrorMessage(" Unique Key Constraint"), HttpStatus.BAD_REQUEST);

        }
        else {
            userService.Insert(hello);
            System.out.println("Successful registration of  - "+mail);

            return new ResponseEntity<>(new ErrorMessage("Registration Successful"), HttpStatus.OK);
        }
    }

    @RequestMapping("/display")
    public List<Users> Display() {
        return userService.Display();
    }
}
