package com.vkraji.webShop.models;

import com.vkraji.webShop.models.cartitem.CartItem;
import com.vkraji.webShop.models.product.Product;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Data
public class Cart {

    @Id
    @GeneratedValue
    private long id;

    @ElementCollection
    private List<CartItem> items;


    public Cart(){
        items = new ArrayList<>();
    }

    public void incrementQuantity(CartItem cartItem){
        for (CartItem item: items) {
            if(item.getId() == cartItem.getId()){
                item.setQuantity(item.getQuantity() + 1);
            }
        }
    }

    public void decrementQuantity(CartItem cartItem) {
        for (CartItem item: items) {
            if(item.getId() == cartItem.getId()){
                item.setQuantity(item.getQuantity() - 1);
            }
        }
    }

    public int getItemCount(){
        return items.size();
    }

    public void add(CartItem item){
        items.add(item);
    }

}
