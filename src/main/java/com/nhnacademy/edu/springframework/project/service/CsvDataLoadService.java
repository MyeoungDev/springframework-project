package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CsvDataLoadService implements DataLoadService {

    @Autowired
    private CsvScores csvScores;

    @Autowired
    private CsvStudents csvStudent;

    @Override
    public void loadAndMerge() {

        csvScores.load();
        csvStudent.load();

        csvStudent.merge(csvScores.findAll());

//        Scores scores = CsvScores.getInstance();
//        scores.load();

//        Students students = CsvStudents.getInstance();
//        students.load();
//        students.merge(scores.findAll());
    }
}
