package com.alex.reservation_app.service;

import com.alex.reservation_app.dto.AuthResponseDto;
import com.alex.reservation_app.dto.LoginDto;
import com.alex.reservation_app.dto.RegisterUserDto;
import com.alex.reservation_app.dto.UserDto;
import com.alex.reservation_app.model.User;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    User registerUser(RegisterUserDto userDto);

    AuthResponseDto loginUser(LoginDto userDto);

    User addAdminUser(RegisterUserDto userDto);

    User getUser(UUID id);

    List<User> getAllUsers();

    Object updateUserById(UUID id, UserDto userDto, Principal principal);

    Object adminUpdateUserById(UUID id, UserDto userDto);


    String deleteUserById(UUID id);
}
