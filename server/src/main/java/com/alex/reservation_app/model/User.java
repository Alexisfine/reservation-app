package com.alex.reservation_app.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity(name = "User")
@Table(
        name = "user_table",
        uniqueConstraints = {
                @UniqueConstraint(name="username_unique", columnNames = "username"),
                @UniqueConstraint(name="email_unique", columnNames = "email")
        }
)
public class User {
    @Id
    @Column(
            name = "id",
            updatable = false
    )
    private UUID id;

    @Column(
            name = "username",
            nullable = false
    )
    private String username;

    @Column(
            name = "password"
    )
    private String password;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Roles> roles = new ArrayList<>();

    @Column(
            name = "created_at"
    )
    private LocalDateTime created_at;

    @Column(
            name = "updated_at"
    )
    private LocalDateTime updated_at;

    public User() {
    }

    public User(
            String username,
            String password,
            String email) {
        this.id = UUID.randomUUID();
        this.username = username;
        this.password = password;
        this.email = email;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                '}';
    }
}
