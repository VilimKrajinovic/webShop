package com.vkraji.webShop.controllers;

import com.vkraji.webShop.models.Category;
import com.vkraji.webShop.models.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

@Controller
public class HomeController {

    public static final String VIEW_NAME = "home";
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public ModelAndView index(@RequestParam(value = "category", required = false) String category) {
        ModelAndView mv = new ModelAndView(VIEW_NAME);

        mv.addObject("categories", Arrays.asList(Category.values()));
        if(category != null){
            mv.addObject("products", productService.getByCategory(category));
        }else{
            mv.addObject("products", productService.getAllProducts());
        }
        return mv;
    }


}
