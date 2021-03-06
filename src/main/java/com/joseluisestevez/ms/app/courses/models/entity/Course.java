package com.joseluisestevez.ms.app.courses.models.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joseluisestevez.ms.commons.exams.models.entity.Exam;
import com.joseluisestevez.ms.commons.students.models.entity.Student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_at")
    private Date createAt;

    @Transient
    private List<Student> students = new ArrayList<>();

    @JsonIgnoreProperties(value = { "course" }, allowSetters = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseStudent> courseStudents = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Exam> exams = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        // You can use the @CreationTimestamp and @UpdateTimestamp attribute annotation
        this.createAt = new Date();
    }

    public void addStudent(Student student) {
        this.students.add(student);
    }

    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    public void addExam(Exam exam) {
        this.exams.add(exam);
    }

    public void removeExam(Exam exam) {
        this.exams.remove(exam);
    }

    public void addSCourseStudent(CourseStudent courseStudent) {
        this.courseStudents.add(courseStudent);
    }

    public void removeCourseStudent(CourseStudent courseStudent) {
        this.courseStudents.remove(courseStudent);
    }
}
