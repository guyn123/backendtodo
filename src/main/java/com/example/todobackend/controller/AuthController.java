package com.example.todobackend.controller;

import com.example.todobackend.dto.AuthResponse;
import com.example.todobackend.dto.LoginRequest;
import com.example.todobackend.dto.RegisterRequest;
import com.example.todobackend.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // 🔹 Register: chỉ trả về message
    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    // 🔹 Login: trả về token
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }
}