package com.hexaware.model;

import jakarta.persistence.*;

@Entity
@Table(name = "user_info")
public class User {

    @Id
    private int id;

    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private boolean isPasswordReset;

    public User() {
    }

    public User(int id, String username, String password, RoleType role, boolean isPasswordReset) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.isPasswordReset = isPasswordReset;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }

    public boolean isPasswordReset() {
        return isPasswordReset;
    }

    public void setPasswordReset(boolean passwordReset) {
        isPasswordReset = passwordReset;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", isPasswordReset=" + isPasswordReset +
                '}';
    }
}
