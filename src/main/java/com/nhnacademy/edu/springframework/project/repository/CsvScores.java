package com.nhnacademy.edu.springframework.project.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvScores implements Scores {


    @Autowired
    private CsvScores csvScores;
    private List<Score> scoreRecord;

    public CsvScores(){
        this.scoreRecord = new ArrayList<>();
    }

    @Override
    public void load() {

        File file = new File("src/main/resources/data/score.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                Score temp = new Score(Integer.parseInt(values[0]), Integer.parseInt(values[1]));
                scoreRecord.add(temp);
            }

//            br.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> findAll() {
        return scoreRecord;
    }
}
