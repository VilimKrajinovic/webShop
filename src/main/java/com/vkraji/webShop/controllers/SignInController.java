package com.vkraji.webShop.controllers;

import com.vkraji.webShop.account.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignInController
{
    @GetMapping
    public String signIn(){
        return "signIn";
    }
}
