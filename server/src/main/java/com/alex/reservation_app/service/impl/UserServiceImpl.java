package com.alex.reservation_app.service.impl;

import com.alex.reservation_app.dao.RoleDao;
import com.alex.reservation_app.dao.UserDao;
import com.alex.reservation_app.dto.RegisterUserDto;
import com.alex.reservation_app.model.Roles;
import com.alex.reservation_app.model.User;
import com.alex.reservation_app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, PasswordEncoder passwordEncoder, RoleDao roleDao) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
    }


    @Override
    public String registerUser(RegisterUserDto userDto) {
        if (userDao.existsByUsername(userDto.getUsername())) {
            throw new IllegalArgumentException("Username " + userDto.getUsername() + " has already been taken");
        }
        if (userDao.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Email " + userDto + " has already been taken");
        }

        User newUser = new User(
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                userDto.getEmail()
        );

        Roles role = roleDao.findByName("USER").get();
        newUser.setRoles(List.of(role));

        userDao.save(newUser);

        return "user registered";
    }
}
