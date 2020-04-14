package com.joseluisestevez.ms.app.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({ "com.joseluisestevez.ms.app.courses.models.entity", "com.joseluisestevez.ms.commons.students.models.entity",
        "com.joseluisestevez.ms.commons.exams.models.entity" })
public class MicroserviceCoursesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceCoursesApplication.class, args);
    }

}
