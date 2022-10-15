package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class ScoresTest {


    ApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project");
    Scores csvScores = context.getBean("csvScores", Scores.class);

    @Test
    void load() {

        csvScores.load();
        assertThat(csvScores.findAll()).isNotEmpty();
//        assertThat(all.stream().count()).isEqualTo(15);
    }

    @Test
    void findAll() {

        csvScores.load();
        List<Score> all = csvScores.findAll();
        assertThat(all.stream().count()).isEqualTo(15);

    }
}