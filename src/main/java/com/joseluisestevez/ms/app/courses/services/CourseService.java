package com.joseluisestevez.ms.app.courses.services;

import com.joseluisestevez.ms.app.courses.models.entity.Course;
import com.joseluisestevez.ms.commons.services.CommonService;

public interface CourseService extends CommonService<Course> {

    Course findCourseByStudentId(Long id);

    Iterable<Long> getExamsAnswered(Long studentId);
}
