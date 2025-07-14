package com.dev.uira.authapi.service;

import com.dev.uira.authapi.controller.MyToken;
import com.dev.uira.authapi.model.User;
import com.dev.uira.authapi.repo.UserRepo;
import com.dev.uira.authapi.security.TokenUtil;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepo repo;

    public UserServiceImpl(UserRepo repo) {
        this.repo = repo;
    }

    @Override
    public User addUser(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return null;
    }

    @Override
    public MyToken userLogin(User user) {
        User storedUser = repo.findByUsername(user.getUsername()).orElseThrow(()->new RuntimeException("User not found"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (encoder.matches(user.getPassword(), storedUser.getPassword())) {
            return TokenUtil.encode(storedUser);
        }
        throw new RuntimeException("Unauthorized user");
    }


}
