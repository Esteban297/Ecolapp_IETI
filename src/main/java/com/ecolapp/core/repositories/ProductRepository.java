package com.ecolapp.core.repositories;

import com.ecolapp.core.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product findProductById(Long id);
}
