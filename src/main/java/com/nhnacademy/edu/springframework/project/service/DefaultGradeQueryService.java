package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class DefaultGradeQueryService implements GradeQueryService {

    private CsvScores csvScores;
    private CsvStudents csvStudents;

    @Autowired
    public DefaultGradeQueryService(CsvScores csvScores, CsvStudents csvStudents) {
        this.csvScores = csvScores;
        this.csvStudents = csvStudents;
    }

    @Override
    public List<Score> getScoreByStudentName(String name) {

        List<Score> list = new ArrayList<>();

        csvStudents.findAll().stream()
                .forEach(student -> {
                    if (student.getName().equals(name)) {
                        list.add(student.getScore());
                    }
                });
        return list;
    }

    @Override
    public Score getScoreByStudentSeq(int seq) {

        return csvScores.findAll().stream()
                .filter(score -> score.getStudentSeq() == seq)
                .findFirst()
                .get();

    }
}
