package com.demo.springdemo.acl.typicode.mapper;

import com.demo.springdemo.acl.typicode.dto.input.User;
import com.demo.springdemo.acl.typicode.dto.output.internal.Usuario;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public Usuario userToUsuario(User user){
        Usuario usuario = Usuario.builder()
                .id(user.getId())
                .nombre(user.getName())
                .correo(user.getEmail())
                .build();
        return usuario;
    }

    public List<Usuario> getUsuarios(User[] users){
        List<Usuario> usuarios = Arrays.stream(users).map(this::userToUsuario)
                .collect(Collectors.toList());
        return usuarios;
    }

}
