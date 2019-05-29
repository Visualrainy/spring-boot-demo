package com.spring.demo;

import com.spring.demo.config.AuthorConfig;
import com.spring.demo.msg.Msg;
import com.spring.demo.support.CustomRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
@EnableCaching
public class DemoApplication implements CommandLineRunner {

    @Autowired
    private JmsTemplate jmsTemplate;

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

    @Override
    public void run(String... args) throws Exception {
        //定义程序启动后执行的代码
        jmsTemplate.send("my-destination", new Msg());
    }
}