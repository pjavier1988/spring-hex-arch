package com.demo.springdemo.acl.typicode.api;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Component
public class UrlsTypicode {

    @Value("${springdemo.typicode}")
    private String serverUrl;

    private String apiGetUsers = "/users";

}
