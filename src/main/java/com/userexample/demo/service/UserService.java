package com.userexample.demo.service;

import com.userexample.demo.domain.User;
import com.userexample.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User findUserByID(String user_id){
        Optional<User> user = userRepository.findByUserId(user_id);
        return user.orElse(null);
    }
}
