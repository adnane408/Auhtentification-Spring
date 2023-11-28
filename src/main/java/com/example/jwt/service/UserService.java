package com.example.jwt.service;

import com.example.jwt.model.Role;
import com.example.jwt.model.User;

import java.util.List;

public interface UserService {
    User saveUser(String username,String pwd,String nama,String confirmPwd);
    Role saveRole(String role);
    void addRoletoUser(String username,String role);
    void removeRoleFromUser(String username,String role);
    User getUser(String username);
    User loadUserByUsername(String username);
    List<User> getUsers();
}
