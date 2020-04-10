package com.joseluisestevez.ms.app.courses.models.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.joseluisestevez.ms.app.courses.models.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

    @Query("select c from Course c join fetch c.students s WHERE s.id = ?1")
    Course findCourseByStudentId(Long id);
}
