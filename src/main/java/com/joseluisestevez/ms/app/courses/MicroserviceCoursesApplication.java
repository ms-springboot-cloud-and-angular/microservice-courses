package com.joseluisestevez.ms.app.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviceCoursesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCoursesApplication.class, args);
    }

}
