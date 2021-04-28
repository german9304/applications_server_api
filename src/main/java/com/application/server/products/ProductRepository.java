package com.application.server.products;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

    @Query("SELECT * FROM product")
    List<Product> getProducts();

    @Query("UPDATE product SET name = :name, price = :price, purchased = :purchased WHERE id = :id RETURNING *;")
    Product update(
            @Param("name") String name,
            @Param("price") String price,
            @Param("purchased") Boolean purchased,
            @Param("id") String id
    );
}
