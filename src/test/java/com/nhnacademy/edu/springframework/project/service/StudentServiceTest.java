package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.StudentService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    ApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project");
    DataLoadService csvDataLoadService = context.getBean("csvDataLoadService", DataLoadService.class);
    StudentService defaultStudentService = context.getBean("defaultStudentService", StudentService.class);

    @Test
    void getPassedStudents() {
        csvDataLoadService.loadAndMerge();
        Collection<Student> passedStudents = defaultStudentService.getPassedStudents();

        Assertions.assertThat(passedStudents.stream().count()).isEqualTo(6);
    }

    @Test
    void getStudentsOrderByScore() {

        csvDataLoadService.loadAndMerge();
        Collection<Student> studentsOrderByScore = defaultStudentService.getStudentsOrderByScore();

        Student firstStudent = studentsOrderByScore.stream().findFirst().get();

        Student lastStudent = studentsOrderByScore.stream()
                .skip(studentsOrderByScore.stream().count() - 1)
                .findFirst()
                .get();

        Assertions.assertThat(firstStudent.getScore().getScore()).isEqualTo(100);
        Assertions.assertThat(lastStudent.getScore().getScore()).isEqualTo(20);
    }
}