package com.example.demoecom.repository;

import com.example.demoecom.model.Category;
import com.example.demoecom.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private JdbcTemplate jdbc;

    public void addProduct(Product product) {
        String sql = "INSERT INTO product (name, price, stock, category_id, is_active) VALUES (?, ?, ?, ?, true)";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setString(1, product.getName());
                pstmt.setDouble(2, product.getPrice());
                pstmt.setInt(3, product.getStock());
                pstmt.setObject(4, product.getCategory()); // Assuming categoryId is directly set in Product
                return pstmt;
            }
        };
        jdbc.update(psc);
    }

    public List<Product> getAllActiveProducts() {
        String sql = "SELECT * FROM product WHERE is_active = true";
        return jdbc.query(sql, (rs, rowNum) -> {
            Product product = new Product();
            product.setId(rs.getInt("id"));
            product.setName(rs.getString("name"));
            product.setPrice(rs.getDouble("price"));
            product.setStock(rs.getInt("stock"));
            return product;
        });
    }

    public List<Product> fetchAllProducts() {
        String sql = "SELECT p.id as product_id, p.name as product_name, p.price, p.stock, "
                + "c.name as category_name "
                + "FROM product p "
                + "JOIN category c ON p.category_id = c.id "
                + "WHERE p.is_active = true"; // Only fetch active products

        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                return con.prepareStatement(sql);
            }
        };

        RowMapper<Product> rowMapper = new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));
                product.setPrice(rs.getDouble("price"));
                product.setStock(rs.getInt("stock"));

                Category category = new Category();
                category.setName(rs.getString("category_name"));
                product.setCategory(category);

                return product;
            }
        };

        return jdbc.query(psc, rowMapper);
    }

    public void softDeleteProduct(int id) {
        String sql = "UPDATE product SET is_active = false WHERE id = ?";
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.setInt(1, id); // Changed to setInt for consistency
                return pstmt;
            }
        };
        jdbc.update(psc);
    }
}