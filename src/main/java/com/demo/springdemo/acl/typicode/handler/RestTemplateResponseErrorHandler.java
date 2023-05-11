package com.demo.springdemo.acl.typicode.handler;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


import com.demo.springdemo.common.exceptions.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.ResponseErrorHandler;


@Slf4j
@Component
public class RestTemplateResponseErrorHandler
        implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return new DefaultResponseErrorHandler().hasError(response);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {

            throw new IOException();

        } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {

            String responseBody="";
            String serverMessage="";
            if (response.getBody() != null ) {
                responseBody = CharStreams.toString(new InputStreamReader(response.getBody(), "UTF-8"));
                ObjectMapper mapper = new ObjectMapper();
                Map<String,Object> map = mapper.readValue(responseBody, Map.class);
                serverMessage = map.get("message").toString();

            }
            int statusCodeResponse = response.getRawStatusCode();
            int statusCodeNotFound = HttpStatus.NOT_FOUND.value();


            if(statusCodeResponse==HttpStatus.METHOD_NOT_ALLOWED.value())
                throw new NotFoundException(serverMessage);

            if(statusCodeResponse==statusCodeNotFound){
                throw new NotFoundException(serverMessage);
            }

        }
        else{
            throw new IOException();
        }
    }


}
