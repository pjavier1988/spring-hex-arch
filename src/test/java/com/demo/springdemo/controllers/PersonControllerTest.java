package com.demo.springdemo.controllers;

import com.demo.springdemo.controller.PersonController;
import com.demo.springdemo.domain.models.Person;
import com.demo.springdemo.service.IPersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

@WebMvcTest(PersonController.class)
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    IPersonService iPersonService;


    @Test
    public void shouldRetriveHttpStatusOKAndAListOfTwoPersonsWhenPerformFindAllGetRequests() throws Exception {
        Person person = Person.builder()
                .userId(1L)
                .userName("test1")
                .fullName("Fullname1")
                .build();

        Person person2 = Person.builder()
                .userId(2L)
                .userName("test2")
                .fullName("Fullname2")
                .build();

        List<Person> expectedistOfUsers = new ArrayList<>();
        expectedistOfUsers.add(person);
        expectedistOfUsers.add(person2);
        when(iPersonService.findAllUsers()).thenAnswer(invocation -> expectedistOfUsers);

        ResultActions resultActions = mockMvc.perform(get("/users/"));
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(expectedistOfUsers.size())))
                .andExpect(jsonPath("$.[0].userName", is("test1")));
    }

    @Test
    public void shouldRetriveHttpStatusOKAndAersonObjectWhenPerformFindAllGetRequests() throws Exception {
        Long givenId = 1L;

        Person person = Person.builder()
                .userId(givenId)
                .userName("test1")
                .fullName("Fullname1")
                .build();




        when(iPersonService.findUserById(anyLong())).thenAnswer(invocation -> person);

        ResultActions resultActions = mockMvc.perform(get("/users/"+givenId));
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.userName", is("test1")));
    }
}
