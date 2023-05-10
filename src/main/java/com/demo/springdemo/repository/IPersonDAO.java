package com.demo.springdemo.repository;

import com.demo.springdemo.domain.models.Person;


import java.util.List;


public interface IPersonDAO {

    List<Person> findAll();

    Person save(Person user);

    Person findById(Long id);
}
