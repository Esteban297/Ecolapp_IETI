package com.ecolapp.core.services;

import com.ecolapp.core.models.Product;
import com.ecolapp.core.models.api_request.UpdateProductStateRequest;
import com.ecolapp.core.models.api_response.AllProductsResponse;
import com.ecolapp.core.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired private ProductRepository productRepository;

    public Product createProduct(Product product) {
        productRepository.save(product);
        return product;
    }

    public AllProductsResponse getAllProducts() {
        return new AllProductsResponse(productRepository.findAll());
    }

    public Product getProductById(Long id) {
        return productRepository.findProductById(id);
    }

    public void updateProductState(UpdateProductStateRequest productUpdate) {
        Product product = productRepository.findProductById(productUpdate.getId());
        if (product != null) {
            product.setState(productUpdate.getState());
            productRepository.save(product);
        }
    }
}
