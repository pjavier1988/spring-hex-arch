package com.demo.springdemo.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationProperties(prefix = "springdemo")
@ConfigurationPropertiesScan
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConfigProperties {
    private String typicode;
}
