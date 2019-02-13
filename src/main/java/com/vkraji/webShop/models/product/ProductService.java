package com.vkraji.webShop.models.product;

import com.vkraji.webShop.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @PostConstruct
    private void init() {
        productRepository.save(new Product("Milk 7g", Category.MILK_CHOCOLATE, 2));
        productRepository.save(new Product("Black 7g", Category.BLACK_CHOCOLATE, 2));
        productRepository.save(new Product("Black 9g", Category.BLACK_CHOCOLATE, 4));
        productRepository.save(new Product("Milk 9g", Category.MILK_CHOCOLATE, 4));
        productRepository.save(new Product("White 15g", Category.WHITE_CHOCOLATE, 10));
        productRepository.save(new Product("Milk 65g", Category.MILK_CHOCOLATE, 25));
        productRepository.save(new Product("Black 65g", Category.BLACK_CHOCOLATE, 25));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductById(Long id){
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getByCategory(String category){
        Category selected = Category.valueOf(category);
        return productRepository.findAllByCategory(selected);
    }
}
