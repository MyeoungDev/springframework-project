package com.nhnacademy.edu.springframework.project.configuration;

import com.nhnacademy.edu.springframework.project.repository.CsvScores;
import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
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
    /* TODO 생성자 주입으로 다 바꾸고 하면 될듯 */
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
