package com.demo.springdemo.services;

import com.demo.springdemo.domain.models.Person;
import com.demo.springdemo.repository.PersonDAO;
import com.demo.springdemo.service.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    PersonService personService;

    @Mock
    PersonDAO personDAO;


    @Test
    void shouldReturnMessageOfFoundPersonGivenAValidId(){
        Long expectedId = 1L;
        String expectedMessage = "Encontre persona con id "+expectedId;
        Person person = Person.builder()
                .userId(expectedId)
                .userName("test1")
                .fullName("Caso de test 1")
                .build();

        when(personDAO.findById(any())).thenReturn(person);

        String message = personService.userExists(expectedId);
        assertThat(message).isEqualTo(expectedMessage);
    }

    @Test
    void shouldReturnMessageOfNotFoundPersonGivenANullObjectPerson(){
        Long expectedId = 1L;
        String expectedMessage ="No existe usuario";


        when(personDAO.findById(any())).thenReturn(null);

        String message = personService.userExists(expectedId);
        assertThat(message).isEqualTo(expectedMessage);
    }
}
