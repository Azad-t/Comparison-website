package com.mycompany.comparison;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTest implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM products", Integer.class);
            System.out.println("Connection successful! Number of products in database: " + count);
        } catch (Exception e) {
            System.err.println("Database connection failed: " + e.getMessage());
        }
    }
}
