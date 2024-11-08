package com.example.demoecom.controller;

import com.example.demoecom.exception.InvalidCredentialsException;
import com.example.demoecom.model.Product;
import com.example.demoecom.model.User;
import com.example.demoecom.service.CustomerService;
import com.example.demoecom.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private CustomerService customerService;
    private final ProductService productService;

    @Autowired
    public UserController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/login-form")
    public String handleCustomerLogin(HttpServletRequest req, HttpSession session) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        try {
            User user = customerService.verifyLogin(username, password);
            if (user.getRole().equalsIgnoreCase("customer")) {
                session.setAttribute("username", username);

                List<Product> products = customerService.getAllActiveProducts();
                req.setAttribute("products", products);
                return "customer-dashboard";
            }
        } catch (InvalidCredentialsException e) {
            req.setAttribute("msg", e.getMessage());
            return "login";
        }
        return null;
    }


    @PostMapping("/add-product")
    public String addProduct(HttpServletRequest req) {

        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int stock = Integer.parseInt(req.getParameter("stock"));

        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        productService.addProduct(product);

        return "customer-dashboard";
    }


    @PostMapping("/buy-product")
    public String buyProduct(HttpServletRequest req, HttpSession session) {
        String customerId = (String) session.getAttribute("customerId");

        if (customerId == null) {
            return "redirect:/login";
        }

        int productId = Integer.parseInt(req.getParameter("productId"));

        customerService.buyProduct(customerId, productId);
        return "redirect:/customer-dashboard";  // Redirect back to the customer dashboard after purchase
    }


    @GetMapping("/purchased-products")
    public String showPurchasedProducts(HttpServletRequest req, HttpSession session) {
        int customerId = (int) session.getAttribute("customerId");
        List<Product> purchasedProducts = customerService.getPurchasedProducts(customerId);
        req.setAttribute("purchasedProducts", purchasedProducts);
        return "purchased-products";
    }
}
