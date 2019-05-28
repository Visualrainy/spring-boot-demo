package com.spring.demo;

import com.spring.demo.config.AuthorConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DemoApplication {

    @Autowired
    private AuthorConfig authorConfig;

    @Value("${project.author}")
    private String projectAuthor;

    @Value("${project.name}")
    private String projectName;


    @RequestMapping("/")
    String index() {
//        return "Project author is " + projectAuthor + "; Project name is " + projectName;
        return "Project author is " + authorConfig.getName() + "; Project name is " + authorConfig.getAge();
    }

    public static void main(String[] args) {
//        SpringApplication.run(DemoApplication.class, args);
        new SpringApplicationBuilder(DemoApplication.class).bannerMode(Banner.Mode.OFF).run(args);
    }

}