package com.ecolapp.core.controllers;

import com.ecolapp.core.models.Product;
import com.ecolapp.core.models.api_request.UpdateProductStateRequest;
import com.ecolapp.core.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

@RestController
@RequestMapping("/api/v1")
public class ProductController {
    @Autowired private ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        productService.createProduct(product);
        try {
            productService.createProduct(product);
            return new ResponseEntity<>("Product was created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/products")
    public ResponseEntity<?> allProducts() {
        try {
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(productService.getProductById(id), HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/product")
    public ResponseEntity<?> updateProductState(@RequestBody UpdateProductStateRequest product) {
        try {
            productService.updateProductState(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
