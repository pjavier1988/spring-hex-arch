package com.demo.springdemo.repository;

import com.demo.springdemo.domain.models.Person;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {


}
