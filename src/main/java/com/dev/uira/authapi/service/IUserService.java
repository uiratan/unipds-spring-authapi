package com.dev.uira.authapi.service;

import com.dev.uira.authapi.model.User;

public interface IUserService {
    public User addUser(User user);
    public User getByUsername(String username);
}
