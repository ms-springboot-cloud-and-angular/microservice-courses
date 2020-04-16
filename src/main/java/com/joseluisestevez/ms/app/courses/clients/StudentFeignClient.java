package com.joseluisestevez.ms.app.courses.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.joseluisestevez.ms.commons.students.models.entity.Student;

@FeignClient(name = "microservice-students")
public interface StudentFeignClient {

    @GetMapping("/students-per-course")
    Iterable<Student> getStudentsPerCourse(@RequestParam Iterable<Long> ids);

}
