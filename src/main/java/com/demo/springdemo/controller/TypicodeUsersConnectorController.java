package com.demo.springdemo.controller;

import com.demo.springdemo.acl.typicode.dto.output.internal.Usuario;
import com.demo.springdemo.service.ITypicodeUsersConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class TypicodeUsersConnectorController {

    @Autowired
    private ITypicodeUsersConnectorService iTypicodeUsersConnectorService;

    @GetMapping("")
    public List<Usuario> getUsuarios(){
        return iTypicodeUsersConnectorService.getUsuarios();
    }

}
