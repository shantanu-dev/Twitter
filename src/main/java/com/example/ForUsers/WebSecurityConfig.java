package com.example.ForUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by shantanu on 8/8/16.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().antMatchers("/login").permitAll()
                .antMatchers("/webjars/angularjs/1.4.9/angular.js").permitAll()
                .antMatchers("/webjars/angularjs/1.4.9/angular-resource.js").permitAll()
                .antMatchers("/webjars/angularjs/1.4.9/angular-route.js").permitAll()
                .antMatchers("/Insert").permitAll()
                .antMatchers("/app.js").permitAll()
                .antMatchers("/Controller.js").permitAll()
                .antMatchers("/insertUser").permitAll()
                .antMatchers("/UserController").permitAll()

                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .permitAll().and().csrf().disable();
           http.userDetailsService(userDetailsService());

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");

        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }
}
