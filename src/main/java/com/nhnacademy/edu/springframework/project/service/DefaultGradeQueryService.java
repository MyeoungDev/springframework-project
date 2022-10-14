package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Score;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.cert.CertStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultGradeQueryService implements GradeQueryService {

    @Autowired
    private CsvScores csvScores;

    @Autowired
    private CsvStudents csvStudents;

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
