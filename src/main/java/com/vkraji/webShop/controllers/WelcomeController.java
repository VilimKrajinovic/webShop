package com.vkraji.webShop.controllers;

import com.vkraji.webShop.models.logs.Log;
import com.vkraji.webShop.models.logs.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.Instant;

@Controller
public class WelcomeController {

    @Autowired
    LogService logService;

    @GetMapping("/welcome")
    public ModelAndView index(Principal principal, HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("welcome");

        logService.save(new Log(principal.getName(), request.getRemoteAddr(), Instant.now()));

        return mv;
    }
}
