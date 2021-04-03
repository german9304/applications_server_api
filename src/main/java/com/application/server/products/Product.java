package com.application.server.products;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    private String id;
    private int price;
    private String name;
}
