package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CsvScores implements Scores {

    private static CsvScores csvScores;
    private List<Score> scoreRecord;

    private CsvScores(){
        this.scoreRecord = new ArrayList<>();
    }

    /** TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Scores getInstance() {
        if (csvScores == null) {
            csvScores = new CsvScores();
        }
        return csvScores;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        try (BufferedReader br = new BufferedReader(new FileReader("D:\\NHNAcademy 강의자료\\spring_core과제\\springframework-project\\src\\main\\resources\\data\\score.csv"))){
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                Score temp = new Score(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                scoreRecord.add(temp);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> findAll() {
        return scoreRecord;
    }
}
