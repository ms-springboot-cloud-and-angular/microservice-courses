package com.joseluisestevez.ms.app.courses.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joseluisestevez.ms.app.courses.clients.AnswerFeignClient;
import com.joseluisestevez.ms.app.courses.clients.StudentFeignClient;
import com.joseluisestevez.ms.app.courses.models.entity.Course;
import com.joseluisestevez.ms.app.courses.models.repository.CourseRepository;
import com.joseluisestevez.ms.commons.services.CommonServiceImpl;
import com.joseluisestevez.ms.commons.students.models.entity.Student;

@Service
public class CourseServiceImpl extends CommonServiceImpl<Course, CourseRepository> implements CourseService {

    @Autowired
    private AnswerFeignClient answerFeignClient;
    @Autowired
    private StudentFeignClient studentFeignClient;

    @Transactional(readOnly = true)
    @Override
    public Course findCourseByStudentId(Long id) {
        return repository.findCourseByStudentId(id);
    }

    @Override
    public Iterable<Long> getExamsAnswered(Long studentId) {
        return answerFeignClient.getExamsAnswered(studentId);
    }

    @Override
    public Iterable<Student> getStudentsPerCourse(Iterable<Long> ids) {
        return studentFeignClient.getStudentsPerCourse(ids);
    }

    @Transactional
    @Override
    public void deleteCourseStudentByStudentId(Long studentId) {
        repository.deleteCourseStudentByStudentId(studentId);
    }

}
