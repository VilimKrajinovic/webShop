package com.vkraji.webShop.controllers;

import com.vkraji.webShop.account.AccountService;
import com.vkraji.webShop.models.Cart;
import com.vkraji.webShop.models.order.Order;
import com.vkraji.webShop.models.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class CheckoutController {

    private static final String CART_NAME = "cart";

    @Autowired
    OrderService orderService;

    @Autowired
    AccountService accountService;

    @GetMapping("/checkout")
    public String index(Principal principal) {
        return "checkout";
    }

    @PostMapping("/checkout/process")
    public String process(Principal principal, HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART_NAME);

        Order order = new Order(accountService.findUserByEmail(principal.getName()), "Gotovina", cart.getItems(), cart.getTotalCost());
        orderService.save(order);

        session.setAttribute(CART_NAME, new Cart());
        return "redirect:/success";
    }

    @PostMapping("/checkout/paypal")
    public String paypal(Principal principal, HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART_NAME);

        Order order = new Order(accountService.findUserByEmail(principal.getName()), "Paypal", cart.getItems(), cart.getTotalCost());
        orderService.save(order);

        session.setAttribute(CART_NAME, new Cart());
        return "redirect:/success";
    }


}
