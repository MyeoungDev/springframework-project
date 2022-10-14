package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.StudentService;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

public class Main {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext("com.nhnacademy.edu.springframework.project");

        DataLoadService dataLoadService = context.getBean("csvDataLoadService", DataLoadService.class);
        dataLoadService.loadAndMerge();
        StudentService studentService = context.getBean("defaultStudentService", StudentService.class);

        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);

    }
}
