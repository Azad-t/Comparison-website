package com.mycompany.comparison.controller;

import com.mycompany.comparison.entity.CartItem;
import com.mycompany.comparison.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public List<CartItem> getCartItems(@RequestParam Long userId) {
        return cartService.getCartItems(userId);
    }

    @PostMapping
    public CartItem addToCart(@RequestBody CartRequest request) {
        return cartService.addToCart(request.getUserId(), request.getProductId());
    }

    @DeleteMapping("/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.removeFromCart(id);
    }

    public static class CartRequest {
        private Long userId;
        private Long productId;

        // Getters and Setters
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }
    }
}
