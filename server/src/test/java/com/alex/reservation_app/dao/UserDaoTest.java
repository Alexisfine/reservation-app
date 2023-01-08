package com.alex.reservation_app.dao;

import com.alex.reservation_app.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    void UserDao_FindByUsername_ReturnsNotNull() {
        // setup
        String username = "User 1";
        User user = User.builder()
                .id(UUID.randomUUID())
                .username(username)
                .email("user@example.com")
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        // exec
        User saveUser = userDao.save(user);

        // assert
        assertThat(saveUser).isNotNull();
        assertThat(saveUser.getUsername()).isEqualTo(username);
    }

    @Test
    void UserDao_ExistsByUsername_ReturnsTrue() {
        // setup
        String username = "User 1";
        User user = User.builder()
                .id(UUID.randomUUID())
                .username(username)
                .email("user@example.com")
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        userDao.save(user);

        // exec
        boolean exist = userDao.existsByUsername(username);

        // assert
        assertThat(exist).isTrue();

    }

    @Test
    void UserDao_ExistsByEmail_ReturnsTrue() {
        // setup
        String username = "User 1";
        String email = "user@example.com";
        User user = User.builder()
                .id(UUID.randomUUID())
                .username(username)
                .email(email)
                .created_at(LocalDateTime.now())
                .updated_at(LocalDateTime.now())
                .build();

        userDao.save(user);

        // exec
        boolean exist = userDao.existsByEmail(email);

        // assert
        assertThat(exist).isTrue();
    }
}