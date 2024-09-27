package com.example.tech_exam_api_java.controller;

import com.example.tech_exam_api_java.model.Product;
import com.example.tech_exam_api_java.model.ProductType;
import com.example.tech_exam_api_java.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Commit;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Scanner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductController productController;

    private static Long productId;

    @Test
    @Commit
    @Order(1)
    public void testCreateProduct() throws Exception {
        Product product = new Product("Hatdog", "Tender Juicy Hatdog", ProductType.food, 10, 200.00, null);

        String response = mockMvc.perform(post("/v1/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hatdog"))
                .andReturn().getResponse().getContentAsString();

        productId = objectMapper.readTree(response).get("id").asLong(); // Store id for future tests
    }

    @Test
    @Order(2)
    public void testGetAllProducts() throws Exception {
        mockMvc.perform(get("/v1/product/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    @Commit
    @Order(3)
    public void testGetProductById() throws Exception {
        mockMvc.perform(get("/v1/product/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Hatdog"));
    }

    @Test
    @Commit
    @Order(4)
    public void testUpdateProduct() throws Exception {
        Product updatedProduct = new Product("Cheesy Hatdog", "Tender Juicy Hatdog with Cheese", ProductType.food, 30, 250.00, null);

        mockMvc.perform(put("/v1/product/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedProduct)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Cheesy Hatdog"))
                .andExpect(jsonPath("$.quantity").value(30));
    }

    @Test
    @Commit
    @Order(5)
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(delete("/v1/product/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}

