package com.application.server.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }

    @Override
    public String create(Product product) {
        return "";
    }

    public @Override Optional<List<Product>> products() {
        List<Product> products = this.productRepository.getProducts();
        if(products.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(products);
    }
}
