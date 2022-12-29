package com.alex.reservation_app.service;

import com.alex.reservation_app.dto.RegisterUserDto;
import com.alex.reservation_app.model.User;

import java.util.Optional;

public interface UserService {

    String registerUser(RegisterUserDto userDto);
}
