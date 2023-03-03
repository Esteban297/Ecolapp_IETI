package com.ecolapp.core.controllers;

import com.ecolapp.core.models.Product;
import com.ecolapp.core.services.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class ProductControllerTest {

    final String baseUrl = "/api/v1";
    @MockBean
    private ProductService productService;
    @Autowired
    private ProductController productController;
    private MockMvc mockMvc;

    private Product product;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(productController).build();
        product = new Product();
        product.setId(1L);
        product.setProductName("Vidrio");
        product.setState("Activo");
    }

    @Test
    void createProduct() throws Exception {
        Product Product = new Product(2L, "Carton", "Vendido");

        when(productService.createProduct(any())).thenReturn(Product);

        String json = "{\"product_name\":\"Carton\",\"product_state\":\"Vendido}";

        mockMvc.perform(post(baseUrl+"/product/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        verify(productService, times(1)).createProduct(any());
    }

    @Test
    void getProductById() throws Exception {

        given(productService.getProductById(1L)).willReturn(product);

        ResultActions response = mockMvc.perform(get(baseUrl + "/product/1"));
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is("1")))
                .andExpect(jsonPath("$.product_name", is("Vidrio")))
                .andExpect(jsonPath("$.state", is("Activo")));

        verify(productService, times(1)).getProductById(1L);
    }

    @Test
    void updateProductState() throws Exception {
        Product Product = new Product(3L, "Cobre", "Vendido");
        when(productService.getProductById(1L)).thenReturn(Product);

        String json = "{\"product_name\":\"Carton\",\"product_state\":\"Vendido}";
        mockMvc.perform(put(baseUrl + "/product/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk());

        verify(productService, times(1)).createProduct(Product);
    }
}