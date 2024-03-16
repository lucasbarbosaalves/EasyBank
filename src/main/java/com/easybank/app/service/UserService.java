package com.easybank.app.service;

import com.easybank.app.model.User;
import com.easybank.app.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    private UserRepository repository;

    public UserService() {
    }

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }

    public User createUser(User user) {
        this.saveUser(user);
        return user;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findById(id).orElseThrow(() -> new Exception("User not found"));
    }
}