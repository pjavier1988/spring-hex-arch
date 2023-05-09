package com.demo.springdemo.service;

import com.demo.springdemo.domain.models.User;
import com.demo.springdemo.repository.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService implements IUserService{

    @Autowired
    private IUserDAO iUserDAO;

    @Override
    public List<User> findAllUsers() {
        return iUserDAO.findAll();
    }

    @Override
    public User createUser(User user) {
        return iUserDAO.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return iUserDAO.findById(id);
    }
}

