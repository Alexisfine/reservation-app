package com.alex.reservation_app.controller;

import com.alex.reservation_app.dto.RegisterUserDto;
import com.alex.reservation_app.dto.UserDto;
import com.alex.reservation_app.model.User;
import com.alex.reservation_app.service.UserService;
import com.alex.reservation_app.utils.R;
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
    public ResponseEntity<R> addAdminUser(@RequestBody RegisterUserDto userDto) {
        return ResponseEntity.ok(new R(userService.addAdminUser(userDto)));
    }

    @GetMapping("/auth/{id}")
    public ResponseEntity<R> getUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(new R(userService.getUser(id)));
    }

    @GetMapping("/auth")
    public ResponseEntity<R> getAllUsers() {
        return ResponseEntity.ok(new R(userService.getAllUsers()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<R> updateUserById(
            @PathVariable("id") UUID id,
            @RequestBody UserDto userDto,
            Principal principal) {
        return ResponseEntity.ok(new R(userService.updateUserById(id, userDto, principal)));
    }

    @PutMapping("/auth/{id}")
    public ResponseEntity<R> adminUpdateUserById(
            @PathVariable("id") UUID id,
            @RequestBody UserDto userDto) {
        return ResponseEntity.ok(new R(userService.adminUpdateUserById(id, userDto)));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<R> deleteUserById(@PathVariable("id") UUID id) {
        return ResponseEntity.ok(new R(userService.deleteUserById(id)));
    }
}
