package com.demo.springdemo.service;

import com.demo.springdemo.domain.models.User;


import java.util.List;


public interface IUserService {

    List<User> findAllUsers();

    User createUser(User user);

    User findUserById(Long id);
}
