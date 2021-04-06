package com.application.server.products;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(ProductsController.class)
@ActiveProfiles("dev")
public class ProductsControllerTests {
    private final String url = "/api/products";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private ProductsService productsService;

    @Test
    public void shouldRespondWithOkStatusWhenProductsExists() throws Exception {
        List<Product> products = new ArrayList<>();
        products.add(new Product("1233", "3333", "jeans", true));
        when(productsService.products()).thenReturn(Optional.of(products));
        this.mockMvc.perform(get(url)).andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
    }

    @Test
    public void shouldRespondWithNotFoundStatusWhenProductsListIsEmpty() throws Exception{
        when(productsService.products()).thenReturn(Optional.empty());
        this.mockMvc.perform(get(url)).andExpect(status().isNotFound()).andExpect(jsonPath("$").isArray());
    }
}
