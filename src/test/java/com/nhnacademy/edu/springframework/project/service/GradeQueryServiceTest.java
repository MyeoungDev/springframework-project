package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeQueryServiceTest {

    @Test
    void getScoreByStudentName() {

        // 내가 찾으려는 학생의 이름
        String findName = "A";

        CsvDataLoadService csvDataLoadService = new CsvDataLoadService();
        csvDataLoadService.loadAndMerge();
        Collection<Student> all = CsvStudents.getInstance().findAll();

        List<Score> scoreByStudentName = new DefaultGradeQueryService().getScoreByStudentName(findName);

        Assertions.assertThat(scoreByStudentName.size()).isEqualTo(2);

    }

    @Test
    void getScoreByStudentSeq() {

        // 내가 찾으려는 학번
        int findSeq = 10;

        new CsvDataLoadService().loadAndMerge();
        Collection<Student> all = CsvStudents.getInstance().findAll();

        Score result = new DefaultGradeQueryService().getScoreByStudentSeq(findSeq);

        Assertions.assertThat(result.getScore()).isEqualTo(50);
    }
}