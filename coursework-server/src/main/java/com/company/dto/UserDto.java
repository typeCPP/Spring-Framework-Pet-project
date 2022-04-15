package com.company.dto;

import com.company.entity.Role;

import java.util.List;

public class UserDto {
    private Integer id;
    private String username;
    private String password;
    private List<Role> roles;
    private String token;

    public UserDto(Integer id, String username, String password, List<Role> roles, String token) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.token = token;
    }

    public UserDto() {
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
