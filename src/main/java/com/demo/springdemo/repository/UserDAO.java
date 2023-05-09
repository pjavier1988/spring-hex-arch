package com.demo.springdemo.repository;

import com.demo.springdemo.domain.models.User;
import com.demo.springdemo.common.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserDAO implements IUserDAO{

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new ObjectNotFoundException("Id no encontrado: "+id));
    }


}
