package com.vkraji.webShop.controllers;

import com.vkraji.webShop.account.Account;
import com.vkraji.webShop.account.AccountService;
import com.vkraji.webShop.models.logs.LogService;
import com.vkraji.webShop.models.order.Order;
import com.vkraji.webShop.models.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class HistoryController {

    public static final String VIEW_NAME = "history";
    @Autowired
    OrderService orderService;

    @Autowired
    AccountService accountService;

    @Autowired
    LogService logService;

    @GetMapping("/history")
    public ModelAndView index(Principal principal) {
        ModelAndView mv = new ModelAndView(VIEW_NAME);
        Account account = accountService.findUserByEmail(principal.getName());
        mv.addObject("account", account);

        List<Order> orders = new ArrayList<>();
        if (account.getRole().equals("ROLE_USER")) {
            orders = orderService.findAllByAccount(account);
        } else if (account.getRole().equals("ROLE_ADMIN")) {
            orders = orderService.findAll();
            mv.addObject("logs", logService.getAllLogs());
        }
        mv.addObject("orders", orders);

        return mv;
    }

}
