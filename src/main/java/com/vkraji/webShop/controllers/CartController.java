package com.vkraji.webShop.controllers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vkraji.webShop.models.Cart;
import com.vkraji.webShop.models.cartitem.CartItem;
import com.vkraji.webShop.models.cartitem.CartItemService;
import com.vkraji.webShop.models.product.Product;
import com.vkraji.webShop.models.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class CartController {

    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    @GetMapping("/cart")
    public ModelAndView index(HttpSession session) {
        ModelAndView mv = new ModelAndView("cart");
        Cart cart = (Cart) session.getAttribute("cart");
        mv.addObject("cart", cart);
        return mv;
    }

    @PostMapping("/cart/add")
    public String insertCartItem(@RequestBody String json, HttpSession session) {

        Cart cart = (Cart) session.getAttribute("cart");
        String selected = extractIdFromJson(json);

        Product prod = productService.findProductById(Long.valueOf(selected));
        CartItem item = cartItemService.save(new CartItem(prod, 1));
        cart.add(item);

        return "redirect:/";
    }

    @PostMapping("cart/increment")
    public ModelAndView incrementQuantitiy(@RequestBody String json, HttpSession session){
        ModelAndView mv = new ModelAndView("cart");
        Cart cart = (Cart) session.getAttribute("cart");
        mv.addObject("cart", cart);

        CartItem item = cartItemService.findCartItemById(Long.valueOf(extractIdFromJson(json)));
        item.increment();
        cartItemService.save(item);

        return mv;
    }

    @PostMapping("cart/decrement")
    public ModelAndView decrementQuantity(@RequestBody String json, HttpSession session){
        ModelAndView mv = new ModelAndView("cart");
        Cart cart = (Cart) session.getAttribute("cart");
        mv.addObject("cart", cart);

        CartItem item = cartItemService.findCartItemById(Long.valueOf(extractIdFromJson(json)));
        if(item.getQuantity() > 1) {
            item.decrement();
        }
        cartItemService.save(item);
        return mv;
    }


    private String extractIdFromJson(String json){
        JsonObject jsonObject = new JsonParser().parse(json).getAsJsonObject();
        JsonElement jsonId = jsonObject.get("selected");
        String selected = jsonId.toString();
        return selected;
    }
}
