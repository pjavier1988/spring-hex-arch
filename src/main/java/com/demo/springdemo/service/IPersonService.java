package com.demo.springdemo.service;

import com.demo.springdemo.domain.models.Person;



import java.util.List;


public interface IPersonService {

    List<Person> findAllUsers();

    Person createUser(Person user);

    Person findUserById(Long id);

    String userExists(Long id);
}
