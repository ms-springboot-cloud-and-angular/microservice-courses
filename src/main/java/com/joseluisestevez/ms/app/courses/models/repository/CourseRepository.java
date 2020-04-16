package com.joseluisestevez.ms.app.courses.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.joseluisestevez.ms.app.courses.models.entity.Course;

public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

    @Query("select c from Course c join fetch c.courseStudents s WHERE s.studentId = ?1")
    Course findCourseByStudentId(Long id);
}
