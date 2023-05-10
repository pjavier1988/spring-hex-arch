package com.demo.springdemo.repositories;

import com.demo.springdemo.common.exceptions.ObjectNotFoundException;
import com.demo.springdemo.domain.models.Person;

import com.demo.springdemo.repository.IPersonDAO;
import com.demo.springdemo.repository.PersonDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DataJpaTest
public class PersonDAOTest {

    @TestConfiguration
    static class UserDAOTestContextConfiguration {
        @Bean
        public IPersonDAO iProcedureDAO() {
            return new PersonDAO();
        }
    }

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PersonDAO userDAO;


    @Test
    public void shouldReturnAPersonWhenCreateAPersonInDatabase(){

        String expectedUsername = "test1";
        Person personExpected = Person.builder()
                .userName("test1")
                .fullName("Fullname1")
                .build();

        Person savedPerson = userDAO.save(personExpected);

        assertThat(savedPerson.getUserName()).isEqualTo(expectedUsername);

    }

    @AfterEach
    public void deleteData(){
        entityManager.clear();
        entityManager.flush();
    }


    @Test
    public void shouldReturnAListOfUsersWhenFindAllIsInvoked(){

        Person person = Person.builder()
                .userName("test1")
                .fullName("Fullname1")
                .build();
        Person personSaved = entityManager.persist(person);
        Person person2 = Person.builder()
                .userName("test2")
                .fullName("Fullname2")
                .build();
        Person personSaved2 = entityManager.persist(person2);
        entityManager.flush();
        List<Person> expectedistOfUsers = new ArrayList<>();
        expectedistOfUsers.add(personSaved);
        expectedistOfUsers.add(personSaved2);


        List<Person> listOfUsersFound = userDAO.findAll();

        assertThat(listOfUsersFound.size()).isEqualTo(expectedistOfUsers.size());


    }


    @Test
    public void shouldReturnAUserWithId1WhenFindById1(){
        String expectedUserName = "test1";

        Person user = Person.builder()
                .userName("test1")
                .fullName("Fullname1")
                .build();
        entityManager.persist(user);
        Person user2 = Person.builder()
                .userName("test2")
                .fullName("Fullname2")
                .build();
        entityManager.persist(user2);
        entityManager.flush();
        Person userFound = userDAO.findById(user.getUserId());

        assertThat(userFound).isNotNull();
    }

    @Test
    public void shouldThrowAObjectNotFoundExceptionGivenANonExistingPersonId(){
        Long givenId = 10L;
        Person user = Person.builder()
                .userName("test1")
                .fullName("Fullname1")
                .build();
        entityManager.persist(user);
        entityManager.flush();

        assertThrows(ObjectNotFoundException.class,()->userDAO.findById(givenId));

    }
}
