package com.demo.springdemo.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String userName;
    private String fullName;
}
