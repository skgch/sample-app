package com.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.entity.User;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer>{

    public User findById(int id);

}
