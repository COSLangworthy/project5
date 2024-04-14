package com.careersforyou.jobservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class project4Application {

    public static void main(String[] args) {

        //Set the external spring configuration
        System.setProperty("spring.config.location", "file:/C:/report/application.properties");

        SpringApplication.run(project4Application.class, args);
    }
}
