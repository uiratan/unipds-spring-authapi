package com.dev.uira.authapi.service;

import com.dev.uira.authapi.controller.MyToken;
import com.dev.uira.authapi.model.User;

public interface IUserService {
    public User addUser(User user);
    public User getByUsername(String username);
    public MyToken userLogin(User user);
}
