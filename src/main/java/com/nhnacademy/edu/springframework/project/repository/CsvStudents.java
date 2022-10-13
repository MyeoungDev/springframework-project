package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


public class CsvStudents implements Students {

    private static CsvStudents csvStudents;

    public Collection<Student> studensRecord;

    public CsvStudents() {
        studensRecord = new ArrayList<>();
    }

    /** TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Students getInstance() {
        if (csvStudents == null) {
            csvStudents = new CsvStudents();
        }
        return csvStudents;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\NHNAcademy 강의자료\\spring_core과제\\springframework-project\\src\\main\\resources\\data\\student.csv"))){
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                Student temp = new Student(Integer.parseInt(values[0]), values[1]);
                studensRecord.add(temp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Student> findAll() {
        return studensRecord;
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        this.studensRecord.stream().forEach(student -> {
            scores.stream().forEach(score -> {
                if (student.getScore() == null) {
                    if (student.getSeq() == score.getStudentSeq()) {
                        student.setScore(score);
                    }
                }
            });
        });
    }
}
