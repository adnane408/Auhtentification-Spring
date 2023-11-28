package com.example.jwt;

import com.example.jwt.model.Role;
import com.example.jwt.model.User;
import com.example.jwt.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class JwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(JwtApplication.class, args);
    }

    @Bean
    CommandLineRunner run(UserService userService){
        return args -> {
            userService.saveRole("USER");
            userService.saveRole("ADMIN");
            userService.saveUser("user1","1234","adnane","1234");
            userService.saveUser("user2","1234","adnane","1234");
            userService.addRoletoUser("user1","USER");
        };
    }

}
