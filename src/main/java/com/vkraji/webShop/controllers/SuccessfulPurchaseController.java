package com.vkraji.webShop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SuccessfulPurchaseController {

    @GetMapping("/success")
    public String index(){
        return "success";
    }
}
