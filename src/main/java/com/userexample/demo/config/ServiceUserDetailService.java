package com.userexample.demo.config;

import com.userexample.demo.domain.User;
import com.userexample.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceUserDetailService implements UserDetailsService {

    @Autowired
    UserService userService;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByID(username);
        ServiceUserDetail serviceUserDetail = new ServiceUserDetail();
        serviceUserDetail.setSeq(user.getSeq());
        serviceUserDetail.setLoginId(user.getUserId());
        serviceUserDetail.setPassword(new SimplePasswordEncoder().encode(user.getPassword()));
        return serviceUserDetail;
    }
}
