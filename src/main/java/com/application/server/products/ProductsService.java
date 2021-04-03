package com.application.server.products;

import java.util.Optional;

public interface ProductsService {

    Optional<Product> findById(String id);

    String create(Product product);

    Optional<Product> products();
}
