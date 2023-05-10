package com.demo.springdemo.repository;


import com.demo.springdemo.common.exceptions.ObjectNotFoundException;
import com.demo.springdemo.domain.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class PersonDAO implements IPersonDAO {

    @Autowired
    private PersonRepository userRepository;

    @Override
    public List<Person> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Person save(Person user) {
        return userRepository.save(user);
    }

    @Override
    public Person findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new ObjectNotFoundException("Id no encontrado: "+id));
    }


}
