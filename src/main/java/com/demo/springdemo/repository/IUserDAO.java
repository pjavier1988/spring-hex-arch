package com.demo.springdemo.repository;

import com.demo.springdemo.domain.models.User;

import java.util.List;


public interface IUserDAO {

    List<User> findAll();

    User save(User user);

    User findById(Long id);
}
