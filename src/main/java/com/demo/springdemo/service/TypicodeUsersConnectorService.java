package com.demo.springdemo.service;

import com.demo.springdemo.acl.typicode.connector.TypicodeUsersConnector;
import com.demo.springdemo.acl.typicode.dto.output.internal.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypicodeUsersConnectorService implements ITypicodeUsersConnectorService{

    @Autowired
    private TypicodeUsersConnector typicodeUsersConnector;
    @Override
    public List<Usuario> getUsuarios() {
        return typicodeUsersConnector.getUsuarios();
    }
}
