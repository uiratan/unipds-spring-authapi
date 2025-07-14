package com.dev.uira.authapi.repo;

import com.dev.uira.authapi.model.User;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface UserRepo extends ListCrudRepository<User,Integer> {
    public Optional<User> findByUsername(String username);
}
