package com.joseluisestevez.ms.app.courses.services;

import com.joseluisestevez.ms.app.courses.models.entity.Course;
import com.joseluisestevez.ms.commons.services.CommonService;
import com.joseluisestevez.ms.commons.students.models.entity.Student;

public interface CourseService extends CommonService<Course> {

    Course findCourseByStudentId(Long id);

    Iterable<Long> getExamsAnswered(Long studentId);

    Iterable<Student> getStudentsPerCourse(Iterable<Long> ids);

    void deleteCourseStudentByStudentId(Long studentId);
}
