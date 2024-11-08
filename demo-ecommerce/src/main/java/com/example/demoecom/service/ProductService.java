package com.example.demoecom.service;

import com.example.demoecom.model.Product;
import com.example.demoecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.fetchAllProducts();
    }

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public void softDeleteProduct(int productId) {
        productRepository.softDeleteProduct(productId);
    }
}