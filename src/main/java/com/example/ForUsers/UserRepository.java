package com.example.ForUsers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by shantanu on 10/8/16.
 */
public interface UserRepository extends JpaRepository<Users, String> {

    List<Users> findByEmail(String email);
    List<Users> findByName(String name);

}


