package com.application.server.products;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductsServiceImpl implements ProductsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Optional<Product> findById(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            log.info("product does not eixsts");
            return Optional.empty();
        }
        return optionalProduct;
    }

    @Override
    public Product update(String id, Product product) {
        Product result = this.productRepository.update(
                product.getName(),
                product.getPrice(),
                product.isPurchased(),
                id
        );
        return result;
    }

    @Override
    public Product create(Product product) throws Exception {
        String sql = "INSERT INTO product (id, price, name, purchased) VALUES (?, ?, ?, ?)";
        List<Object[]> args = new ArrayList<>();
        Object[] object = {
                product.getId(),
                product.getPrice(),
                product.getName(),
                product.isPurchased()
        };
        args.add(object);
        jdbcTemplate.batchUpdate(sql, args);
        return  product;
    }

    @Override
    public Optional<List<Product>> products() {
        List<Product> products = this.productRepository.getProducts();
        if(products.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(products);
    }
}
