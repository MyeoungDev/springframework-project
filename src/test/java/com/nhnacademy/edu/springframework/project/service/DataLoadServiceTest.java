package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class DataLoadServiceTest {

    @Test
    void loadAndMerge() {

        CsvDataLoadService csvDataLoadService = new CsvDataLoadService();
        csvDataLoadService.loadAndMerge();

        Collection<Student> all = CsvStudents.getInstance().findAll();

        Student firstStudent = all.stream().findFirst().get();
        Student lastStudent = all.stream().skip(all.stream().count() - 1).findFirst().get();

        Assertions.assertThat(firstStudent.getScore()).isNotNull();
        Assertions.assertThat(lastStudent.getScore()).isNotNull();
    }
}