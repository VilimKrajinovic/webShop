package com.vkraji.webShop.models.cartitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class CartItemService {

    @Autowired
    CartItemRepository cartItemRepository;

    @PostConstruct
    private void init() {
    }

    public List<CartItem> getAllProducts() {
        return cartItemRepository.findAll();
    }

    public CartItem findCartItemById(Long id){
        return cartItemRepository.findById(id).orElse(null);
    }

    public CartItem save(CartItem cartItem){
        return cartItemRepository.save(cartItem);
    }

}
