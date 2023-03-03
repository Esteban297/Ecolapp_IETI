package com.ecolapp.core.services;

import com.ecolapp.core.models.Product;
import com.ecolapp.core.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        product = new Product();
        product.setId(1L);
        product.setProductName("Vidrio");
        product.setState("Activo");
    }

    @Test
    void createProduct() {
        Product productNew = new Product(2l, "Carton", "Vendido");
        Mockito.when(productRepository.save(productNew)).thenReturn(productNew);
        Product productSaved = productService.createProduct(productNew);
        Assertions.assertNotNull(productSaved);
        Assertions.assertEquals("Carton", productSaved.getProductName());
    }

    @Test
    void getAllProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));
        assertNotNull(productService.getAllProducts());
    }

    @Test
    void getProductById() {
        when(productRepository.findProductById(2L)).thenReturn(product);
        Product product = productService.getProductById(2L);
        Assertions.assertNotNull(product);
        Assertions.assertEquals("Vidrio", product.getProductName());
    }
}