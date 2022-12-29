package com.alex.reservation_app.dao;

import com.alex.reservation_app.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDao extends JpaRepository<Roles, Integer> {
    Optional<Roles> findByName(String name);
}
