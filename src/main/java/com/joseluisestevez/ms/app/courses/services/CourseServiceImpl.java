package com.joseluisestevez.ms.app.courses.services;

import org.springframework.stereotype.Service;

import com.joseluisestevez.ms.app.courses.models.entity.Course;
import com.joseluisestevez.ms.app.courses.models.repository.CourseRepository;
import com.joseluisestevez.ms.commons.services.CommonServiceImpl;

@Service
public class CourseServiceImpl extends CommonServiceImpl<Course, CourseRepository> implements CourseService {

}
