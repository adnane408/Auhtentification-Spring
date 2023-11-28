package com.example.jwt.api;

import com.example.jwt.model.Role;
import com.example.jwt.model.RoleToUser;
import com.example.jwt.model.User;
import com.example.jwt.model.Role;
import com.example.jwt.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserRessources {
    private final UserService userService;

    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @GetMapping("/profile")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri=URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user.getUsername(),user.getPassword(),user.getName(), user.getPassword()));
    }
    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        return ResponseEntity.ok().body(userService.saveRole(role.getName()));
    }
    @PostMapping("/role/addRU")
    public void addROleToUser(@RequestBody RoleToUser form){
        userService.addRoletoUser(form.getUsername(), form.getRoleName());
    }

}
