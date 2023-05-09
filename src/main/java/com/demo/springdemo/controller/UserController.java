package com.demo.springdemo.controller;

import com.demo.springdemo.domain.dto.UserDTO;
import com.demo.springdemo.domain.models.User;
import com.demo.springdemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getAllUSers(){
        List<User> listUsers = iUserService.findAllUsers();
        List<UserDTO> listUsersDTO = listUsers.stream().map(this::modelTODTO)
                .collect(Collectors.toList());
        return new ResponseEntity<List<UserDTO>>(listUsersDTO,HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(name = "userId") Long userId){
       User userFound = iUserService.findUserById(userId);
       UserDTO userDTO = modelTODTO(userFound);
       return new ResponseEntity<UserDTO>(userDTO,HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<UserDTO>  insertUser(@RequestBody UserDTO userDTO){
        User user = dtoToModel(userDTO);
        User userCreated = iUserService.createUser(user);
        UserDTO userResponse = modelTODTO(userCreated);
        return new ResponseEntity<UserDTO>(userResponse, HttpStatus.CREATED);
    }


    private UserDTO modelTODTO(User user){

        UserDTO userDTO = UserDTO.builder()
                .userName(user.getUserName())
                .fullName(user.getFullName())
                .build();

        return userDTO;
    }

    private User dtoToModel(UserDTO userDTO){

        User user = User.builder()
                .userName(userDTO.getUserName())
                .fullName(userDTO.getFullName())
                .build();

        return user;
    }

}
