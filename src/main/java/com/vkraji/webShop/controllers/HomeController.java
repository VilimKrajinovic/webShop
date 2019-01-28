package com.vkraji.webShop.controllers;

import com.vkraji.webShop.models.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ModelAndView index(Principal principal, HttpSession session) {
        ModelAndView mv = new ModelAndView("home");

        if(principal != null){
            mv.addObject("principal", principal);
        }
        mv.addObject("products", productService.getAllProducts());
        return mv;
    }

}
