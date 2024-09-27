package com.example.tech_exam_api_java.controller;

import com.example.tech_exam_api_java.model.Product;
import com.example.tech_exam_api_java.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/v1/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        // Check first if product exists
        Optional<Product> product = productService.getProductById(id);

        if (product.isPresent()) {
            // Return product if exists
            return ResponseEntity.ok(product);
        } else {
            // Return not found if not existing
            Map<String, String> response = new HashMap<>();
            response.put("error", "Product not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        // Check first if product exists
        Optional<Product> product = productService.getProductById(id);

        if (product.isPresent()) {
            // Update product if exists
            Product updatedProduct = productService.updateProduct(id, productDetails);
            return ResponseEntity.ok(updatedProduct);
        } else {
            // Return not found if not existing
            Map<String, String> response = new HashMap<>();
            response.put("error", "Product not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        // Check first if product exists
        Optional<Product> product = productService.getProductById(id);

        if (product.isPresent()) {
            // Delete product if exists
            productService.deleteProduct(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Product deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            // Return not found if not existing
            Map<String, String> response = new HashMap<>();
            response.put("error", "Product not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
