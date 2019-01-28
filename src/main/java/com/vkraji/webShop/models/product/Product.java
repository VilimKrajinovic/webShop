package com.vkraji.webShop.models.product;

import com.vkraji.webShop.models.Category;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue()
    private long id;

    private String name;
    private Category category;
    private int price;

    public Product(){

    }

    public Product(String name, Category category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }
}
