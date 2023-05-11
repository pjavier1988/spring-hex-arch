package com.demo.springdemo.service;

import com.demo.springdemo.acl.typicode.dto.output.internal.Usuario;

import java.util.List;

public interface ITypicodeUsersConnectorService {

    List<Usuario> getUsuarios();

}
