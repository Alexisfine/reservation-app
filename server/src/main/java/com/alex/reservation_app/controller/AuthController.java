package com.alex.reservation_app.controller;

import com.alex.reservation_app.dao.RoleDao;
import com.alex.reservation_app.dao.UserDao;
import com.alex.reservation_app.dto.RegisterUserDto;
import com.alex.reservation_app.model.User;
import com.alex.reservation_app.security.JwtGenerator;
import com.alex.reservation_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/v1")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;

    @Autowired
    public AuthController(
            AuthenticationManager authenticationManager,
            UserService userService,
            RoleDao roleDao,
            JwtGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleDao = roleDao;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterUserDto userDto) {
        return ResponseEntity.ok(userService.registerUser(userDto));
    }
}
