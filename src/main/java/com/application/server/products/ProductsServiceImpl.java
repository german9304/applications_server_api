package com.application.server.products;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductsServiceImpl implements ProductsService {

    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }

    @Override
    public String create(Product product) {
        return "";
    }

    public @Override Optional<Product> products() {
        return Optional.empty();
    }
}
