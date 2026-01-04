package com.proyecto.proyecto.controller;

import com.proyecto.proyecto.dto.*;
import com.proyecto.proyecto.entity.User;
import com.proyecto.proyecto.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        User user = userService.register(request);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        System.out.println("ENTRÓ AL LOGIN");
        return ResponseEntity.ok(userService.login(request));
    }

}
