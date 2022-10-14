package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Component
public class CsvStudents implements Students {

    @Autowired
    private CsvStudents csvStudents;

    private Collection<Student> studensRecord;

    @Autowired
    public void setCsvStudents(CsvStudents csvStudents) {
        this.csvStudents = csvStudents;
    }

    public CsvStudents() {
        studensRecord = new ArrayList<>();
    }

    @Override
    public void load() {

        File file = new File("src/main/resources/data/student.csv");
//        String absolutePath = file.getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()))){

                String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                Student temp = new Student(Integer.parseInt(values[0]), values[1]);
                studensRecord.add(temp);
            }

//            br.close();
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


/* TODO -> 1. 싱글톤으로 되어있는것을 @Autowired로 바꿔주는 작업들 진행
*   2. Main 실행 ApplicationContext 로 객체 가져와서 실행시키기 */
