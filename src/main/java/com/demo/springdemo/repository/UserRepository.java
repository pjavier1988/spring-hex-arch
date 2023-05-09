package com.demo.springdemo.repository;

import com.demo.springdemo.domain.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {


}
