package com.userexample.demo.service;

import com.userexample.demo.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    void findUserTest() {
        User dev = userService.findUserByID("dev");
        System.out.println(dev);
    }

}