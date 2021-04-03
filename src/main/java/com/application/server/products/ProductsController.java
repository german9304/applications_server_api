package com.application.server.products;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    private final String api = "/api/products/";

    @PostMapping(api + "create")
    public ResponseEntity<String> createProduct(
            @RequestParam("locale") String locale,
            @RequestBody Product product
            ) {
        return ResponseEntity.status(HttpStatus.CREATED).body("product created");
    }
}
