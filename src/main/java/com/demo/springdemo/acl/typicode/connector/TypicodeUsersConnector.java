package com.demo.springdemo.acl.typicode.connector;

import com.demo.springdemo.acl.typicode.api.UrlsTypicode;
import com.demo.springdemo.acl.typicode.dto.input.User;
import com.demo.springdemo.acl.typicode.dto.output.internal.Usuario;
import com.demo.springdemo.acl.typicode.mapper.UsuarioMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Component
public class TypicodeUsersConnector {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UrlsTypicode urlsTypicode;

    public List<Usuario> getUsuarios(){
        String url = urlsTypicode.getServerUrl()+urlsTypicode.getApiGetUsers();

        ResponseEntity<User[]> response = restTemplate.getForEntity(url, User[].class);
        if(response.getStatusCodeValue() == HttpStatus.OK.value()){
            User[] body = response.getBody();
            UsuarioMapper mapper = new UsuarioMapper();
            return mapper.getUsuarios(body);
        }
        return new ArrayList<>();
    }
}
