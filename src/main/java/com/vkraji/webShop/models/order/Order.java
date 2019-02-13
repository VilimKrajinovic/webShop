package com.vkraji.webShop.models.order;

import com.vkraji.webShop.account.Account;
import com.vkraji.webShop.models.cartitem.CartItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.security.Principal;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "order_table")
@Data
@NoArgsConstructor
public class Order {

    @Transient
    DateTimeFormatter formatter =
            DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT )
                    .withLocale( Locale.UK )
                    .withZone( ZoneId.systemDefault() );

    @Id
    @GeneratedValue()
    private long id;

    @OneToOne
    private Account user;
    @ElementCollection
    private List<CartItem> items;
    private String orderType;
    private Instant orderTime;
    private int totalPrice;

    public Order(Account user, String orderType, List<CartItem> items, int totalPrice) {
        this.user = user;
        this.orderType = orderType;
        this.items = items;
        this.totalPrice = totalPrice;
        orderTime = Instant.now();
    }

    public String getTime(){
        return formatter.format(orderTime);
    }


}
