package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sample.entity.User;
import com.sample.repository.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("username is empty");
        }

        User user = repository.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("user [" + username + "] is not found");
        }

        return user;
    }

}
