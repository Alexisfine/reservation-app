package com.alex.reservation_app.service.impl;

import com.alex.reservation_app.dao.RoleDao;
import com.alex.reservation_app.dao.UserDao;
import com.alex.reservation_app.dto.AuthResponseDto;
import com.alex.reservation_app.dto.LoginDto;
import com.alex.reservation_app.dto.RegisterUserDto;
import com.alex.reservation_app.dto.UserDto;
import com.alex.reservation_app.exception.IllegalOperationException;
import com.alex.reservation_app.exception.UserNotFoundException;
import com.alex.reservation_app.model.Roles;
import com.alex.reservation_app.model.User;
import com.alex.reservation_app.security.CustomerUserDetailsService;
import com.alex.reservation_app.security.JwtGenerator;
import com.alex.reservation_app.service.UserService;
import com.alex.reservation_app.utils.UpdateColumnUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    private RoleDao roleDao;
    private PasswordEncoder passwordEncoder;

    private AuthenticationManager authenticationManager;
    private JwtGenerator jwtGenerator;
    private UserDetailsService userDetailsService;



    @Autowired
    public UserServiceImpl(UserDao userDao,
                           PasswordEncoder passwordEncoder,
                           RoleDao roleDao,
                           AuthenticationManager authenticationManager,
                           JwtGenerator jwtGenerator,
                           UserDetailsService userDetailsService) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public User registerUser(RegisterUserDto userDto) {
        User newUser = validateUserExistenceAndCreateNewUser(userDto);

        Roles role = roleDao.findByName("USER").get();
        newUser.setRoles(List.of(role));

        User savedUser = userDao.save(newUser);

        savedUser.setPassword(null);

        return (savedUser);
    }

    @Override
    public AuthResponseDto loginUser(LoginDto userDto) {
        User user = userDao
                .findByUsername(userDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Cannot find user"));

        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userDto.getUsername(),
                                userDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        user.setPassword(null);
        return new AuthResponseDto(token,user);

    }

    @Override
    public User addAdminUser(RegisterUserDto userDto) {

        User newUser = validateUserExistenceAndCreateNewUser(userDto);

        Roles admin = roleDao.findByName("ADMIN").get();
        Roles user = roleDao.findByName("USER").get();
        newUser.setRoles(List.of(admin, user));

        User savedUser = userDao.save(newUser);
        savedUser.setPassword(null);
        return savedUser;
    }



    @Override
    public User getUser(UUID id) {
        User user = findUser(id);
        user.setPassword(null);
        return user;
    }



    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public Object updateUserById(UUID id, UserDto userDto, Principal principal) {
        // Check if user exists
        User user = findUser(id);

        // Check if user is updating him/her self
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!user.getUsername().equals(name)) {
            throw new IllegalOperationException("You can only update your account");
        }

        return updatedUserHelper(userDto, user);

    }


    @Override
    public Object adminUpdateUserById(UUID id, UserDto userDto) {
        // Check if user exists
        User user = findUser(id);
        return updatedUserHelper(userDto, user);
    }

    @Override
    public String deleteUserById(UUID id) {
        User user = findUser(id);

        boolean isAdmin = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getAuthorities()
                .stream().anyMatch(e -> e.getAuthority().equals("ADMIN"));

        if (isAdmin) {
            userDao.deleteById(id);
        } else {
            String tokenUsername = SecurityContextHolder
                    .getContext()
                    .getAuthentication()
                    .getName();
            if (!tokenUsername.equals(user.getUsername()))
                throw new IllegalOperationException("You can only delete your account");
            userDao.deleteById(id);
        }
        return "User is deleted";
    }

    private Object updatedUserHelper(UserDto userDto, User user) {
        if (userDto.getPassword() != null) {
            String encodePassword = passwordEncoder.encode(userDto.getPassword());
            userDto.setPassword(encodePassword);

            // Update token
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    userDto.getUsername(),
                                    userDto.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);

            BeanUtils.copyProperties(userDto, user, UpdateColumnUtil.getNullPropertyNames(userDto));
            User savedUser = userDao.save(user);
            savedUser.setPassword(null);

            return new AuthResponseDto(token, savedUser);
        }

        BeanUtils.copyProperties(userDto, user, UpdateColumnUtil.getNullPropertyNames(userDto));
        User savedUser = userDao.save(user);
        savedUser.setPassword(null);
        return savedUser;
    }

    private User findUser(UUID id) {
        User user = userDao.findById(id)
                .orElseThrow(()->new UserNotFoundException("Cannot find user"));
        return user;
    }


    private User validateUserExistenceAndCreateNewUser(RegisterUserDto userDto) {
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
        return newUser;
    }


}
