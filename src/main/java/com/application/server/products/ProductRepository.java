package com.application.server.products;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT * FROM product")
    List<Product> getProducts();
}
