package com.example.ForUsers;

/**
 * Created by shantanu on 10/8/16.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


/**
 * Created by shantanu on 9/8/16.
 */
@Service
public class UserService implements UserDetailsService

    {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public List<Users> findByName(String name) {
        return userRepository.findByName(name);
    }

    @Transactional
    public List<Users> Display() {
        return userRepository.findAll();
    }

    @Transactional
    public boolean Insert(Users xyz) {
        if (userRepository.save(xyz) != null) {
            return true;
        } else
            return false;
    }


    @Override
    @Transactional

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
        // Declare a null Spring User



        // Search database for a user that matches the specified username
        // You can provide a custom DAO to access your persistence layer
        // Or use JDBC to access your database
        // DbUser is our custom domain user. This is not the same as Spring's User
        Users dbUser = userRepository.findByEmail(email).get(0);

        if (dbUser == null) {
            throw new UsernameNotFoundException("HiMVC Security:: Error in retrieving user(username=" + email + ")");
        }


        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                dbUser.getEmail(),
                dbUser.getPassword(),
                true,
                true,
                true,
                true,

                loadUserAuthorities(dbUser.getName())

        );

        return userDetails;
    }

    public Collection<? extends GrantedAuthority> loadUserAuthorities(String username) {
        List<SimpleGrantedAuthority> auths = new java.util.ArrayList<SimpleGrantedAuthority>();
        auths.add(new SimpleGrantedAuthority(username));
        return auths;
    }}
