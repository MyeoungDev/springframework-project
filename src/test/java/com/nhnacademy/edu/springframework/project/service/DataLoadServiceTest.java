package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {

    @Test
    void loadAndMerge() {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project");
        Scores csvScores = context.getBean("csvScores", Scores.class);
        Students csvStudents = context.getBean("csvStudents", Students.class);

        csvScores.load();
        csvStudents.load();

        csvStudents.merge(csvScores.findAll());

        csvStudents.findAll().stream().forEach(student -> System.out.println(student));
    }
}