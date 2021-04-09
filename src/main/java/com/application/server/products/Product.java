package com.application.server.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Product {

    @Id
    private String id;
    private String price;
    private String name;
    private boolean purchased;
}
