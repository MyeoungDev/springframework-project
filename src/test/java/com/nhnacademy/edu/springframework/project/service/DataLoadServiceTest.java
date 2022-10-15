package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

        Score firstScore = csvStudents.findAll().stream()
                .findFirst()
                .get().getScore();


        Assertions.assertThat(firstScore).isNotNull();
    }
}