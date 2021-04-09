package com.application.server.products;

import org.springframework.dao.DataAccessException;
import org.springframework.data.relational.core.sql.SQL;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface ProductsService {

    Optional<Product> findById(String id);

    Product create(Product product) throws Exception;

    Optional<List<Product>> products();
}
