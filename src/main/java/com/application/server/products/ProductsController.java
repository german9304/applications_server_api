package com.application.server.products;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    private final String api = "/api/products";

    @PostMapping(api + "/create")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        try {
            log.info("adding product");
            this.productsService.create(product);
        }catch (Exception e) {
            log.info("error: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("could not save product");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("product created");
    }

    /**
     * Fetch products
     *
     * @return
     */
    @GetMapping(api)
    public ResponseEntity<List<Product>> getProducts() {
        var products = this.productsService.products();
        if(products.isEmpty()) {
            List<Product> emptyProducts = List.of();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emptyProducts);
        }
        return ResponseEntity.status(HttpStatus.OK).body(products.get());
    }


}
