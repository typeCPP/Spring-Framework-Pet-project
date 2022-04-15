package com.company.courseworkclient;

import com.company.courseworkclient.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CourseworkClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseworkClientApplication.class, args);
    }

    @Bean
    public User createUser() {
        return new User();
    }


}
