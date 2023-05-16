package com.userexample.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {

    @GetMapping(value = "/")
    public String indexPageReturn(){
        return "index";
    }
}
