package com.joseluisestevez.ms.app.courses.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joseluisestevez.ms.app.courses.models.entity.Course;
import com.joseluisestevez.ms.app.courses.services.CourseService;
import com.joseluisestevez.ms.commons.controllers.CommonController;
import com.joseluisestevez.ms.commons.students.models.entity.Student;

@RestController
public class CourseController extends CommonController<Course, CourseService> {

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody Course course) {
        Optional<Course> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Course currentCourse = optional.get();
        currentCourse.setName(course.getName());

        Course courseSaved = service.save(currentCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseSaved);
    }

    @PutMapping("/{id}/assign-students")
    public ResponseEntity<?> assignStudents(@PathVariable Long id, @RequestBody List<Student> students) {
        Optional<Course> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Course currentCourse = optional.get();

        students.forEach(s -> {
            currentCourse.addStudent(s);
        });

        Course courseSaved = service.save(currentCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseSaved);
    }

    @PutMapping("/{id}/delete-student")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id, @RequestBody Student student) {
        Optional<Course> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Course currentCourse = optional.get();
        currentCourse.removeStudent(student);

        Course courseSaved = service.save(currentCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseSaved);
    }

}
