package com.vkraji.webShop.models.order;

import com.vkraji.webShop.account.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public Order save(Order order){
        return orderRepository.save(order);
    }

    public List<Order> findAllByAccount(Account account){
        return orderRepository.findAllByUser(account);
    }

    public List<Order> findAll(){
        return orderRepository.findAll();
    }
}
