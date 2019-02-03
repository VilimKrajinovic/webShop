package com.vkraji.webShop.models.order;

import com.vkraji.webShop.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUser(Account user);
}
