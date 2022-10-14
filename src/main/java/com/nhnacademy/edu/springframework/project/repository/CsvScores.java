package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CsvScores implements Scores {


    private CsvScores csvScores;
    private List<Score> scoreRecord;

    @Autowired
    public void setCsvScores(CsvScores csvScores) {
        this.csvScores = csvScores;
    }

    private CsvScores(){
        this.scoreRecord = new ArrayList<>();
    }

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
