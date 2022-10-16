package com.nhnacademy.edu.springframework.project.configuration;

import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultGradeQueryService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.nhnacademy.edu.springframework.project.service")
public class ServerConfig {

    @Autowired
    private JavaConfig javaConfig;
    @Bean
    public CsvDataLoadService CsvDataLoadService() {
        return new CsvDataLoadService(javaConfig.csvScores(), javaConfig.csvStudents());
    }

    @Bean
    public DefaultGradeQueryService defaultGradeQueryService() {
        return new DefaultGradeQueryService(javaConfig.csvScores(), javaConfig.csvStudents());
    }

    @Bean
    public DefaultStudentService defaultStudentService() {
        return new DefaultStudentService((javaConfig.csvStudents()));
    }

}
