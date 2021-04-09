package com.application.server.products;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jdk.jfr.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
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
        Product newProduct = new Product("33333", "socks", "3000", false);
        newProduct.setPurchased(false);
        products.add(newProduct);
        when(productsService.products()).thenReturn(Optional.of(products));
        this.mockMvc.perform(get(url)).andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
    }

    @Test
    public void shouldRespondWithNotFoundStatusWhenProductsListIsEmpty() throws Exception{
        when(productsService.products()).thenReturn(Optional.empty());
        this.mockMvc.perform(get(url)).andExpect(status().isNotFound()).andExpect(jsonPath("$").isArray());
    }

    @Test
    public void shouldCreateAProductWithCreatedStatusResponse() throws Exception {
        Product newProduct = new Product("111", "200", "jeans", false);
        when(productsService.create(newProduct)).thenReturn(newProduct);
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer();
        String jsonProduct = writer.writeValueAsString(newProduct);
        this.mockMvc.perform(post(url + "/create")
                .content(jsonProduct)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string("product created"));
    }

    @Test
    public void shouldThrowException() throws Exception {
        Product newProduct = new Product("111", "200", "jeans", false);
        when(productsService.create(newProduct)).thenThrow(new Exception(""));
        ObjectMapper mapper = new ObjectMapper();
        ObjectWriter writer = mapper.writer();
        String jsonProduct = writer.writeValueAsString(newProduct);
        this.mockMvc.perform(post(url + "/create")
                .content(jsonProduct)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("could not save product"));
    }
}
