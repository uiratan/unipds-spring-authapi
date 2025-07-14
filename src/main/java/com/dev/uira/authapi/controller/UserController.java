package com.dev.uira.authapi.controller;

import com.dev.uira.authapi.model.User;
import com.dev.uira.authapi.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final IUserService service;

    public UserController(IUserService service) {
        this.service = service;
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user) {
        return ResponseEntity.status(201).body(service.addUser(user));
    }

}
