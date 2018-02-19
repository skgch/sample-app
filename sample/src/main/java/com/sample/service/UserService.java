package com.sample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findById(int id) {
        return repository.findById(id);
    }

    public Iterable<User> findAll() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(email)) {
            throw new UsernameNotFoundException("username is empty");
        }

        User user = repository.findByEmail(email);

        if(user == null) {
            throw new UsernameNotFoundException("user not found for name: " + email);
        }

        return user;
    }

    public User save(String name, String email, String password) {
        User user = new User();

        user.setName(name);

        email = email.toLowerCase();
        user.setEmail(email);

        String passwordDigest = passwordEncoder.encode(password);
        user.setPassword(passwordDigest);

        return repository.saveAndFlush(user);
    }

}
