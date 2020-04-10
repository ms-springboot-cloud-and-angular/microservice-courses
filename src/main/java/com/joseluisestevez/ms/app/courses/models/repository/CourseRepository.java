package com.joseluisestevez.ms.app.courses.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.joseluisestevez.ms.app.courses.models.entity.Course;

public interface CourseRepository extends CrudRepository<Course, Long> {

}
