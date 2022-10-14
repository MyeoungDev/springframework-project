package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GradeQueryServiceTest {

    ApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project");

    DataLoadService csvDataLoadService = context.getBean("csvDataLoadService", DataLoadService.class);
    Students csvStudent = context.getBean("csvStudents", Students.class);
    GradeQueryService defaultGradeQueryService = context.getBean("defaultGradeQueryService", GradeQueryService.class);


    @Test
    void getScoreByStudentName() {

        // 내가 찾으려는 학생의 이름
        String findName = "A";

        csvDataLoadService.loadAndMerge();

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

//
//        new CsvDataLoadService().loadAndMerge();
//        Collection<Student> all = CsvStudents.getInstance().findAll();
//
//        Score result = new DefaultGradeQueryService().getScoreByStudentSeq(findSeq);
//
//        Assertions.assertThat(result.getScore()).isEqualTo(50);
    }
}