package com.sample.service;

import org.hsqldb.lib.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(email)) {
            throw new UsernameNotFoundException("username is empty");
        }

        User user = repository.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("user not found for name: " + email);
        }

        return user;
    }

    public User save(String name, String email, String password) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        beforeSave(user);
        return repository.saveAndFlush(user);
    }

    public User update(int id, String name, String email, String password) {
        User user = repository.findById(id);
        user.setName(name);
        user.setEmail(email);
        if(!StringUtil.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
        }
        beforeSave(user);
        return repository.saveAndFlush(user);
    }

    public void delete(int id) {
        repository.delete(id);
    }

    private User beforeSave(User user) {
        String email = user.getEmail().toLowerCase();
        user.setEmail(email);

//        String password = user.getPassword();
//        String passwordDigest = passwordEncoder.encode(password);
//        user.setPassword(passwordDigest);

        user.setRole("ROLE_USER");
        return user;
    }

}
