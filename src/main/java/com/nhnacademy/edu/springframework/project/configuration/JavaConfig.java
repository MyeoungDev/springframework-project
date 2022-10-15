package com.nhnacademy.edu.springframework.project.configuration;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.nhnacademy.edu.springframework.project.repository")
@EnableAspectJAutoProxy
public class JavaConfig {

    @Bean
    public CsvStudents csvStudents() {
        return new CsvStudents();
    }

    @Bean
    public CsvScores csvScores() {
        return new CsvScores();
    }

}
