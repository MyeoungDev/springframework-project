package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {

    @Test
    void load() {

        Collection<Student> testList = getList();

        assertThat(testList).isNotEmpty();
    }
    @Test
    void findAll() {

        Collection<Student> testList = getList();

        assertThat(testList.size()).isEqualTo(3);
    }

    private static Collection<Student> getList() {
        Collection<Student> testList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\NHNAcademy 강의자료\\spring_core과제\\springframework-project\\src\\test\\resources\\data\\score.csv"))){
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                Student temp = new Student(Integer.parseInt(values[0]), values[1]);
                testList.add(temp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return testList;
    }
}