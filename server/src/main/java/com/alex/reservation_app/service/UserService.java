package com.alex.reservation_app.service;

import com.alex.reservation_app.dto.AuthResponseDto;
import com.alex.reservation_app.dto.LoginDto;
import com.alex.reservation_app.dto.RegisterUserDto;
import com.alex.reservation_app.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface UserService {

    User registerUser(RegisterUserDto userDto);

    AuthResponseDto  loginUser(LoginDto userDto);
}
