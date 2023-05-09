package com.demo.springdemo.repositories;

import com.demo.springdemo.domain.models.User;

import com.demo.springdemo.repository.IUserDAO;
import com.demo.springdemo.repository.UserDAO;
import com.demo.springdemo.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class UserDAOTest {

    @TestConfiguration
    static class UserDAOTestContextConfiguration {
        @Bean
        public IUserDAO iProcedureDAO() {
            return new UserDAO();
        }
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserDAO userDAO;


    @Test
    public void shouldReturnAUserUsersWhenCreateAUserInDatabase(){
        User userExpected = User.builder()
                .userName("test1")
                .fullName("Fullname1")
                .build();
        userDAO.save(userExpected);
        entityManager.flush();
        System.out.println("TEST!");
        assertThat("Fullname1").isEqualTo(userExpected.getUserName());


    }



    @Test
    public void shouldReturnAListOfUsersWhenFindAllIsInvoked(){
        User user = User.builder()
                .userId(1L)
                .userName("test1")
                .fullName("Fullname1")
                .build();
        User userSaved = entityManager.persist(user);
        User user2 = User.builder()
                .userId(2L)
                .userName("test2")
                .fullName("Fullname2")
                .build();
        User userSaved2 = entityManager.persist(user2);
        entityManager.flush();
        List<User> expectedistOfUsers = new ArrayList<>();
        expectedistOfUsers.add(userSaved);
        expectedistOfUsers.add(userSaved2);


        List<User> listOfUsersFound = userDAO.findAll();

        assertThat(listOfUsersFound.size()).isEqualTo(expectedistOfUsers.size());


    }


    @Test
    public void shouldReturnAUserWithId1WhenFindById1(){
        String expectedUserName = "test1";

        User user = User.builder()
                .userId(1L)
                .userName("test1")
                .fullName("Fullname1")
                .build();
        User userSaved = entityManager.persist(user);
        User user2 = User.builder()
                .userId(2L)
                .userName("test2")
                .fullName("Fullname2")
                .build();
        User userSaved2 = entityManager.persist(user2);

        entityManager.flush();


        User userFound = userDAO.findById(user.getUserId());



        assertThat(userFound).isNotNull();


    }
}
