package com.application.server.products;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Product {

    private String id;
    private String price;
    private String name;
    private boolean purchased;
}
