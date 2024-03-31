package com.wkrzyz.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * controller for the login page
 * */
@Controller
public class LoginController {

    /**
     * This method return a thymeleaf template for the login page that replaces the generic Oauth2 one
     * @return "login" a thymeleaf page
     * */
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
}
