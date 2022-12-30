package com.alex.reservation_app.controller;

import com.alex.reservation_app.dto.RegisterUserDto;
import com.alex.reservation_app.dto.UserDto;
import com.alex.reservation_app.model.User;
import com.alex.reservation_app.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users/v1")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/auth")
    public ResponseEntity<User> addAdminUser(@RequestBody RegisterUserDto userDto) {
        return ResponseEntity.ok(userService.addAdminUser(userDto));
    }

    @GetMapping("/auth/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(userService.getUser(id));
    }

    @GetMapping("/auth")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUserById(
            @PathVariable("id") UUID id,
            @RequestBody UserDto userDto,
            Principal principal) {
        return ResponseEntity.ok(userService.updateUserById(id, userDto, principal));
    }

    @PutMapping("/auth/{id}")
    public ResponseEntity<Object> adminUpdateUserById(
            @PathVariable("id") UUID id,
            @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.adminUpdateUserById(id, userDto));
    }






}
