package com.example.ForUsers;

/**
 * Created by shantanu on 8/8/16.
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("../static/home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/insertUser").setViewName("../static/insertUser");
    }

}