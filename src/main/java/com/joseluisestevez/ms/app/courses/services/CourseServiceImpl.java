package com.joseluisestevez.ms.app.courses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joseluisestevez.ms.app.courses.clients.AnswerFeignClient;
import com.joseluisestevez.ms.app.courses.models.entity.Course;
import com.joseluisestevez.ms.app.courses.models.repository.CourseRepository;
import com.joseluisestevez.ms.commons.services.CommonServiceImpl;

@Service
public class CourseServiceImpl extends CommonServiceImpl<Course, CourseRepository> implements CourseService {

    @Autowired
    private AnswerFeignClient answerFeignClient;

    @Transactional(readOnly = true)
    @Override
    public Course findCourseByStudentId(Long id) {
        return repository.findCourseByStudentId(id);
    }

    @Override
    public Iterable<Long> getExamsAnswered(Long studentId) {
        return answerFeignClient.getExamsAnswered(studentId);
    }

}
