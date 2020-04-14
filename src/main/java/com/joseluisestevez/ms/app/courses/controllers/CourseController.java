package com.joseluisestevez.ms.app.courses.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.joseluisestevez.ms.app.courses.models.entity.Course;
import com.joseluisestevez.ms.app.courses.services.CourseService;
import com.joseluisestevez.ms.commons.controllers.CommonController;
import com.joseluisestevez.ms.commons.exams.models.entity.Exam;
import com.joseluisestevez.ms.commons.students.models.entity.Student;

@RestController
public class CourseController extends CommonController<Course, CourseService> {

    @Value("${config.loadbalancer.test}")
    private String balancerTest;

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody Course course, BindingResult result) {
        if (result.hasErrors()) {
            return this.validate(result);
        }
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

        students.forEach(currentCourse::addStudent);

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

    @GetMapping("/student/{id}")
    public ResponseEntity<?> findByStudent(@PathVariable Long id) {
        Course course = service.findCourseByStudentId(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }

        List<Long> examIds = (List<Long>) service.getExamsAnswered(id);
        List<Exam> exams = course.getExams().stream().map(e -> {
            if (examIds.contains(e.getId())) {
                e.setAnswered(true);
            }
            return e;
        }).collect(Collectors.toList());

        course.setExams(exams);

        return ResponseEntity.ok(course);
    }

    @PutMapping("/{id}/assign-exams")
    public ResponseEntity<?> assignExams(@PathVariable Long id, @RequestBody List<Exam> exams) {
        Optional<Course> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Course currentCourse = optional.get();

        exams.forEach(currentCourse::addExam);

        Course courseSaved = service.save(currentCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseSaved);
    }

    @PutMapping("/{id}/delete-exam")
    public ResponseEntity<?> deleteExam(@PathVariable Long id, @RequestBody Exam exam) {
        Optional<Course> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Course currentCourse = optional.get();
        currentCourse.removeExam(exam);

        Course courseSaved = service.save(currentCourse);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseSaved);
    }

    @GetMapping("balancer-test")
    public ResponseEntity<?> balancerTest() {
        Map<String, Object> response = new HashMap<>();
        response.put("balancerTest", balancerTest);
        response.put("courses", service.findAll());
        return ResponseEntity.ok(response);
    }

}
