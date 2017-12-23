package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.entity.User;
import com.sample.repository.UsersRepository;

@Service
@Transactional
public class UserService {

    @Autowired
    private UsersRepository repository;

    public User findById(int id) {
        return repository.findById(id);
    }

    public Iterable<User> findAll() {
        return repository.findAll();
    }

    public User save(User user) {
        String email = user.getEmail().toLowerCase();
        user.setEmail(email);
        return repository.saveAndFlush(user);
    }

}
