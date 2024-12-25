package com.mycompany.comparison.controller;

import com.mycompany.comparison.entity.Product;
import com.mycompany.comparison.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String name) {
        //输出日志
        System.out.println("searchProducts: " + name);
        return productService.searchProducts(name);
    }

    @GetMapping("/scrape")
    public List<Product> scrapeProducts(@RequestParam String name) {
        try {
            // 输出日志
            System.out.println("scrapeProducts: " + name);
            return productService.scrapeProductsByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
