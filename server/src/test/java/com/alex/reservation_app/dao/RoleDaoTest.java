package com.alex.reservation_app.dao;

import com.alex.reservation_app.model.Roles;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class RoleDaoTest {
    @Autowired
    private RoleDao roleDao;

    @Test
    void RoleDao_FindByName_ReturnsNotNull() {
        // setup
        String roleAdmin = "ROLE_ADMIN";
        Roles role = Roles.builder()
                .name(roleAdmin)
                .build();
        roleDao.save(role);

        // exec
        Roles roles = roleDao.findByName(roleAdmin).get();
        // assert
        Assertions.assertThat(roles).isNotNull();

    }
}