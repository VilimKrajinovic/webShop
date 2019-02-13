package com.vkraji.webShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    public static final String VIEW_NAME = "login";

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView(VIEW_NAME);
    }
}
