package com.vkraji.webShop.models.cartitem;

import com.vkraji.webShop.models.product.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cartItem")
@Data
@NoArgsConstructor
public class CartItem {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity){
        this.product = product;
        this.quantity = quantity;
    }

    public void increment(){
        this.quantity++;
    }

    public void decrement(){
        this.quantity--;
    }
}
