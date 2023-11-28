package com.example.jwt.service;

import com.example.jwt.model.Role;
import com.example.jwt.model.User;
import com.example.jwt.repository.RoleRepo;
import com.example.jwt.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(String username, String pwd, String nama, String confirmPwd) {
        User appUser=userRepo.findByUsername(username);
        if(appUser!=null) throw new RuntimeException("This User Already Exist");
        if(!pwd.equals(confirmPwd)) throw new RuntimeException("Password not match");
        User user= User.builder().id(UUID.randomUUID().getMostSignificantBits())
                .username(username)
                .password(passwordEncoder.encode(pwd))
                .build();
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(String role) {
        return roleRepo.save(Role.builder().name(role).build());
    }

    @Override
    public void addRoletoUser(String username, String role) {
        User appUser=userRepo.findByUsername(username);
        Role appRole=roleRepo.findByName(role);
        appUser.getRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String role) {
        User appUser=userRepo.findByUsername(username);
        Role appRole=roleRepo.findByName(role);
        appUser.getRoles().remove(appRole);

    }
    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public User loadUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public List<User> getUsers() {
        return userRepo.findAll();
    }
}
