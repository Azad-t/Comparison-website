package com.mycompany.comparison.controller;

import com.mycompany.comparison.entity.User;
import com.mycompany.comparison.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @PostMapping("/login")
    public User login(@RequestBody Map <String,String> loginInfo) {
        String username = loginInfo.get("username");
        String password = loginInfo.get("password");
        return userService.authenticate(username, password);
    }
}
