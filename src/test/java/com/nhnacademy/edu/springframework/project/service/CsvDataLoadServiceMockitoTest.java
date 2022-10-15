package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.configuration.JavaConfig;
import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class CsvDataLoadServiceMockitoTest {

    @InjectMocks
    CsvScores csvScores;

    @InjectMocks
    CsvStudents csvStudents;

    @InjectMocks
    CsvDataLoadService csvDataLoadService;


    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        csvDataLoadService = new CsvDataLoadService(csvScores, csvStudents);

//        csvDataLoadService = new CsvDataLoadService(csvScores, csvStudents);
    }

    @Test
    void loadAndMerge() {

        csvDataLoadService.loadAndMerge();

        Score firstScore = csvStudents.findAll().stream()
                .findFirst()
                .get().getScore();


        Assertions.assertThat(firstScore).isNotNull();

    }
}