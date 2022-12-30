package com.alex.reservation_app.security;

import com.alex.reservation_app.dao.UserDao;
import com.alex.reservation_app.exception.UserNotFoundException;
import com.alex.reservation_app.model.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerUserDetailsService implements UserDetailsService {
    private UserDao userDao;

    @Autowired
    public CustomerUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.alex.reservation_app.model.User user = userDao
                .findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Username not found"));

        mapRolesToAuthorities(user.getRoles()).stream().toList().forEach(e -> System.out.println(e.getClass()));
        System.out.println(mapRolesToAuthorities(user.getRoles()));
        return new User(
                user.getUsername(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles())
        );
    }

    private Collection<GrantedAuthority> mapRolesToAuthorities(List<Roles> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
