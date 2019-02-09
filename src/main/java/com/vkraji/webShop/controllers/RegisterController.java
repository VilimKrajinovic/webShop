package com.vkraji.webShop.controllers;

import com.vkraji.webShop.account.Account;
import com.vkraji.webShop.account.AccountService;
import com.vkraji.webShop.models.register.RegisterForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    private static final String REGISTER_VIEW_NAME = "register";

    @Autowired
    private AccountService accountService;

    @GetMapping("register")
    public String index(Model model){
        model.addAttribute(new RegisterForm());
        return REGISTER_VIEW_NAME;
    }

    @PostMapping("register")
    public String signUp(@Valid @ModelAttribute RegisterForm registerForm, Errors errors){
        if(errors.hasErrors()){
            return REGISTER_VIEW_NAME;
        }
        Account account = accountService.save(registerForm.createAccount());
        accountService.signIn(account);
        return "redirect:/welcome";
    }
}
