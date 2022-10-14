package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {



    ApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project");
    Students csvStudents = context.getBean("csvStudents", Students.class);

    Scores csvScores = context.getBean("csvScores", Scores.class);

    @Test
    void load() {

        csvStudents.load();
        assertThat(csvStudents.findAll()).isNotEmpty();
    }

    @Test
    void findAll() {

        csvStudents.load();

        assertThat(csvStudents.findAll().stream().count()).isEqualTo(15);
    }

    @Test
    void merge() {

        csvScores.load();
        csvStudents.load();
        csvStudents.merge(csvScores.findAll());

        Student firstStudent = csvStudents.findAll().stream()
                .findFirst()
                .get();
        Student lastStudent = csvStudents.findAll().stream()
                .skip(csvStudents.findAll().stream().count() - 1)
                .findFirst()
                .get();

        assertThat(firstStudent.getScore()).isNotNull();
        assertThat(lastStudent.getScore()).isNotNull();

    }
}