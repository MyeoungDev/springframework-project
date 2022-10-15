package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.configuration.JavaConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class)
class CsvScoresMockitoTest {

    @InjectMocks
    private CsvScores csvScores;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        csvScores.load();
    }

    @Test
    void load() {
        assertThat(csvScores.findAll()).isNotEmpty();
    }

    @Test
    void findAll() {
        assertThat(csvScores.findAll().stream().count()).isEqualTo(15);
    }
}