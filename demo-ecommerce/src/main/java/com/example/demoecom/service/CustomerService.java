package com.example.demoecom.service;

import com.example.demoecom.exception.InvalidCredentialsException;
import com.example.demoecom.model.Product;
import com.example.demoecom.model.User;
import com.example.demoecom.repository.CustomerRepository;
import com.example.demoecom.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public User verifyLogin(String username, String password) throws InvalidCredentialsException {
        return customerRepository.verifyLogin(username, password);
    }

    public List<Product> getAllActiveProducts() {
        return productRepository.getAllActiveProducts(); // Updated to use the new method
    }

    public void buyProduct(String customerId, int productId) {
        customerRepository.buyProduct(customerId, productId);
    }

    public List<Product> getPurchasedProducts(int customerId) {
        return customerRepository.getPurchasedProducts(customerId);
    }
}