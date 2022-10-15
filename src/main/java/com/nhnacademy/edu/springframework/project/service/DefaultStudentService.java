package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class DefaultStudentService implements StudentService {

    CsvStudents csvStudents;

    @Autowired
    public DefaultStudentService(CsvStudents csvStudents) {
        this.csvStudents = csvStudents;
    }
    @Override
    public Collection<Student> getPassedStudents() {

        return csvStudents.findAll()
                .stream()
                .filter(s -> !s.getScore().isFail())
                .collect(Collectors.toList());
    }

    @Override
    public Collection<Student> getStudentsOrderByScore() {

        return csvStudents.findAll()
                .stream()
                .sorted((s1, s2) -> s2.getScore().getScore() - s1.getScore().getScore())
                .collect(Collectors.toList());
    }
}
