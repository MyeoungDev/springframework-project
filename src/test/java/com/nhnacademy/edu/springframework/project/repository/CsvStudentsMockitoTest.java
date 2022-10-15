package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CsvStudentsMockitoTest {

    @InjectMocks
    private CsvStudents csvStudents;

    @InjectMocks
    private CsvScores csvScores;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        csvStudents.load();
        csvScores.load();
    }

    @Test
    void load() {
        assertThat(csvStudents.findAll()).isNotEmpty();
    }

    @Test
    void findAll() {
        assertThat(csvStudents.findAll().stream().count()).isEqualTo(15);
    }

    @Test
    void merge() {
        csvStudents.merge(csvScores.findAll());

        Student firstStudent = csvStudents.findAll().stream()
                .findFirst()
                .get();
        Student lastStudent = csvStudents.findAll().stream()
                .skip(csvStudents.findAll().stream().count() - 1)
                .findFirst()
                .get();

        assertThat(firstStudent.getScore()).isNotNull();
        assertThat(lastStudent.getScore()).isNotNull();
    }
}