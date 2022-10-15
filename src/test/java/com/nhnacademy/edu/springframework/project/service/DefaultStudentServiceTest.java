package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DefaultStudentServiceTest {

    @InjectMocks
    CsvScores csvScores;

    @InjectMocks
    CsvStudents csvStudents;

    @InjectMocks
    CsvDataLoadService csvDataLoadService;

    @InjectMocks
    DefaultStudentService defaultStudentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        csvDataLoadService = new CsvDataLoadService(csvScores, csvStudents);
        defaultStudentService = new DefaultStudentService(csvStudents);
        csvDataLoadService.loadAndMerge();
    }

    @Test
    void getPassedStudents() {

        Collection<Student> passedStudents = defaultStudentService.getPassedStudents();

        Assertions.assertThat(passedStudents.stream().count()).isEqualTo(6);
    }

    @Test
    void getStudentsOrderByScore() {

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