package com.example.demoecom.repository;

import com.example.demoecom.exception.InvalidCredentialsException;
import com.example.demoecom.model.Product;
import com.example.demoecom.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Verify login credentials
    public User verifyLogin(String username, String password) throws InvalidCredentialsException {
        String sql = "select * from User where username=? and password=?";
        PreparedStatementCreator psc = con -> {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            return pstmt;
        };
        List<User> list = jdbcTemplate.query(psc, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(username);
                user.setPassword(password);
                user.setRole(rs.getString("role"));
                return user;
            }
        });
        if (list.isEmpty()) {
            throw new InvalidCredentialsException("Invalid Credentials");
        } else {
            return list.get(0);
        }
    }

    // Buy a product
    public void buyProduct(String customerId, int productId) {
        String sql = "insert into purchased_products(customer_id, product_id) values(?, ?)";
        jdbcTemplate.update(sql, customerId, productId);
    }



    // Get purchased products
    public List<Product> getPurchasedProducts(int customerId) {
        String sql = "select p.* from purchased_products pp join product p on pp.product_id = p.id where pp.customer_id=?";
        return jdbcTemplate.query(sql, new Object[]{customerId}, new RowMapper<Product>() {
            @Override
            public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                return product;
            }
        });
    }
}