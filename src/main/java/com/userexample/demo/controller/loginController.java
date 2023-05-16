package com.userexample.demo.controller;

import com.userexample.demo.config.ServiceUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.List;

@Controller
public class loginController {

    @Autowired
    SessionRegistry sessionRegistry;

    @Autowired
    ServiceUserDetailService serviceUserDetailService;

    @GetMapping(value = "/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping(value ="/checkUser")
    @ResponseBody
    public boolean checkUser(@RequestParam(name = "username") String userid) {
        //TODO session 뒤지는 함수 구현필요
        UserDetails userDetails = serviceUserDetailService.loadUserByUsername(userid);
        List<SessionInformation> allSessions = sessionRegistry.getAllSessions(userDetails,false);
        if(allSessions.size() > 0)
            return true; // user 존재
        else
            return false; // user 존재 x
    }
}
