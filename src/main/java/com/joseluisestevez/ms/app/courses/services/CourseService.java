package com.joseluisestevez.ms.app.courses.services;

import org.springframework.web.bind.annotation.RequestBody;

import com.joseluisestevez.ms.app.courses.models.entity.Course;
import com.joseluisestevez.ms.commons.services.CommonService;
import com.joseluisestevez.ms.commons.students.models.entity.Student;

public interface CourseService extends CommonService<Course> {

    Course findCourseByStudentId(Long id);

    Iterable<Long> getExamsAnswered(Long studentId);

    Iterable<Student> getStudentsPerCourse(@RequestBody Iterable<Long> ids);
}
