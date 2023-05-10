package com.demo.springdemo.service;

import com.demo.springdemo.domain.models.Person;

import com.demo.springdemo.repository.IPersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PersonService implements IPersonService {

    @Autowired
    private IPersonDAO iUserDAO;

    @Override
    public List<Person> findAllUsers() {
        return iUserDAO.findAll();
    }

    @Override
    public Person createUser(Person user) {
        return iUserDAO.save(user);
    }

    @Override
    public Person findUserById(Long id) {
        return iUserDAO.findById(id);
    }

    @Override
    public String userExists(Long id) {
        Person person = iUserDAO.findById(id);
        if(person != null){
            return "Encontre persona con id "+id;
        }
        return "No existe usuario";
    }
}

