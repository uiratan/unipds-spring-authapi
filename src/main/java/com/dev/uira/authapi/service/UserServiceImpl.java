package com.dev.uira.authapi.service;

import com.dev.uira.authapi.model.User;
import com.dev.uira.authapi.repo.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User addUser(User user) {
        return repo.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }
}
