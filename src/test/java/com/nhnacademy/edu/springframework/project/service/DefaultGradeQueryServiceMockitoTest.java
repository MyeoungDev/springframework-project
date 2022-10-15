package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DefaultGradeQueryServiceMockitoTest {

    @InjectMocks
    CsvScores csvScores;

    @InjectMocks
    CsvStudents csvStudents;

    @InjectMocks
    DefaultGradeQueryService defaultGradeQueryService;

    @InjectMocks
    CsvDataLoadService csvDataLoadService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        csvDataLoadService = new CsvDataLoadService(csvScores, csvStudents);
        defaultGradeQueryService = new DefaultGradeQueryService(csvScores, csvStudents);
        csvDataLoadService.loadAndMerge();
    }

    @Test
    void getScoreByStudentName() {
        String findName = "A";
        List<Score> scoreByStudentName = defaultGradeQueryService.getScoreByStudentName(findName);
        Assertions.assertThat(scoreByStudentName.size()).isEqualTo(2);
    }

    @Test
    void getScoreByStudentSeq() {
        // 내가 찾으려는 학번
        int findSeq = 10;

        csvDataLoadService.loadAndMerge();
        Score result = defaultGradeQueryService.getScoreByStudentSeq(findSeq);
        Assertions.assertThat(result.getScore()).isEqualTo(50);
    }
}