package com.vkraji.webShop.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vkraji.webShop.models.Cart;
import com.vkraji.webShop.models.cartitem.CartItem;
import com.vkraji.webShop.models.cartitem.CartItemService;
import com.vkraji.webShop.models.helpers.JsonUtil;
import com.vkraji.webShop.models.product.Product;
import com.vkraji.webShop.models.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class CartController {

    private static final String VIEW_NAME = "cart";
    private static final String CART_NAME = "cart";

    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    @GetMapping("/cart")
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView(VIEW_NAME);
        Cart cart = (Cart) session.getAttribute(CART_NAME);
        mv.addObject(CART_NAME, cart);
        return mv;
    }

    @PostMapping("/cart/add")
    public String insertCartItem(@RequestBody String json, HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART_NAME);
        String selected = JsonUtil.extractIdFromJson(json);

        Product prod = productService.findProductById(Long.valueOf(selected));
        CartItem item = cartItemService.save(new CartItem(prod, 1));
        cart.add(item);

        return "redirect:/";
    }

    @PostMapping("cart/increment")
    public ModelAndView incrementQuantitiy(@RequestBody String json, HttpSession session) {
        ModelAndView mv = new ModelAndView(VIEW_NAME);
        Cart cart = (Cart) session.getAttribute(CART_NAME);
        mv.addObject(CART_NAME, cart);

        CartItem item = cartItemService.findCartItemById(Long.valueOf(JsonUtil.extractIdFromJson(json)));
        cart.incrementQuantity(item);
        cartItemService.save(item);

        return mv;
    }

    @PostMapping("cart/decrement")
    public ModelAndView decrementQuantity(@RequestBody String json, HttpSession session) {
        ModelAndView mv = new ModelAndView(VIEW_NAME);
        Cart cart = (Cart) session.getAttribute(CART_NAME);
        mv.addObject(CART_NAME, cart);

        CartItem item = cartItemService.findCartItemById(Long.valueOf(JsonUtil.extractIdFromJson(json)));
        if (item.getQuantity() > 1) {
            cart.decrementQuantity(item);
        }
        cartItemService.save(item);
        return mv;
    }

    @PostMapping("cart/delete")
    public ModelAndView delete(@RequestBody String json, HttpSession session) {
        ModelAndView mv = new ModelAndView(VIEW_NAME);
        Cart cart = (Cart) session.getAttribute(CART_NAME);
        mv.addObject(CART_NAME, cart);

        CartItem item = cartItemService.findCartItemById(Long.valueOf(JsonUtil.extractIdFromJson(json)));
        cart.delete(item);

        return mv;
    }

    @GetMapping("cart/getTotalCost")
    public @ResponseBody
    int getTotatalCost(HttpSession session) {
        Cart cart = (Cart) session.getAttribute(CART_NAME);
        return Optional.ofNullable(cart)
                .map(Cart::getTotalCost)
                .orElse(0);
    }


}
