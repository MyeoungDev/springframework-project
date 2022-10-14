package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {


    @Test
    void load() {

        Collection<Student> testList = getList();

        assertThat(testList).isNotEmpty();

    }

    @Test
    void findAll() {

        Collection<Student> testList = getList();

        assertThat(testList.size()).isEqualTo(4);

    }

    @Test
    void merge() {

        Student st1 = new Student(1, "A");
        Student st2 = new Student(2, "B");
        Student st3 = new Student(3, "A");
        Student st4 = new Student(4, "D");

        Score sc1 = new Score(1, 30);
        Score sc2 = new Score(2, 80);
        Score sc3 = new Score(3, 70);

        Collection<Score> testScoreList = new ArrayList<>(
                Arrays.asList(sc1, sc2, sc3)
        );

        Collection<Student> testStudentsList = new ArrayList<>(
                Arrays.asList(st1, st2, st3, st4)
        );

        testStudentsList.stream().forEach(student -> {
            testScoreList.stream().forEach(score -> {
                if (student.getScore() == null) {
                    if (student.getSeq() == score.getStudentSeq()) {
                        student.setScore(score);
                    }
                }
            });
        });

        st1.setScore(new Score(1, 30));
        st2.setScore(new Score(2, 80));
        st3.setScore(new Score(3, 70));

        Collection<Student> expectation = new ArrayList<>(Arrays.asList(st1, st2, st3, st4));

        assertThat(expectation).hasSameElementsAs(testStudentsList);
    }

    private static Collection<Student> getList() {
        Collection<Student> testList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("D:\\NHNAcademy 강의자료\\spring_core과제\\springframework-project\\src\\test\\resources\\data\\student.csv"))){
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