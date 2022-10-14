package com.nhnacademy.edu.springframework.project.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void getPassedStudents() {
        new CsvDataLoadService().loadAndMerge();

        Collection<Student> passedStudents = new DefaultStudentService().getPassedStudents();

        Assertions.assertThat(passedStudents.stream().count()).isEqualTo(6);
    }

    @Test
    void getStudentsOrderByScore() {

        new CsvDataLoadService().loadAndMerge();

        Collection<Student> studentsOrderByScore = new DefaultStudentService().getStudentsOrderByScore();

        Student firstStudent = studentsOrderByScore.stream().findFirst().get();

        Student lastStudent = studentsOrderByScore.stream()
                .skip(studentsOrderByScore.stream().count() - 1)
                .findFirst()
                .get();

        Assertions.assertThat(firstStudent.getScore().getScore()).isEqualTo(100);
        Assertions.assertThat(lastStudent.getScore().getScore()).isEqualTo(20);
    }
}