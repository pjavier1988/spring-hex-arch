package com.demo.springdemo.common.payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponseObject{

    private String message;
    private String date;
    private String url;


}
