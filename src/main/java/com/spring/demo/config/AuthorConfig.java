package com.spring.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "author")
@PropertySource("classpath:author.properties")
public class AuthorConfig {


    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String age;
}
