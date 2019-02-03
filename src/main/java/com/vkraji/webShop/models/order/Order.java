package com.vkraji.webShop.models.order;

import com.vkraji.webShop.account.Account;
import com.vkraji.webShop.models.cartitem.CartItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.Principal;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "order_table")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue()
    private long id;

    @OneToOne
    private Account user;
    @ElementCollection
    private List<CartItem> items;
    private String orderType;
    private Instant orderTime;

    public Order(Account user, String orderType, List<CartItem> items) {
        this.user = user;
        this.orderType = orderType;
        this.items = items;
        orderTime = Instant.now();
    }
}
