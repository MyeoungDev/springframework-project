package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.repository.CsvStudents;
import com.nhnacademy.edu.springframework.project.repository.Students;
import com.nhnacademy.edu.springframework.project.service.CsvDataLoadService;
import com.nhnacademy.edu.springframework.project.service.DataLoadService;
import com.nhnacademy.edu.springframework.project.service.DefaultStudentService;
import com.nhnacademy.edu.springframework.project.service.Student;

import java.util.Collection;

public class Main {

    // TODO 9 - 성공적으로 실행되어야 합니다.
    public static void main(String[] args) {
        DataLoadService dataLoadService = new CsvDataLoadService();
        dataLoadService.loadAndMerge();

        DefaultStudentService studentService = new DefaultStudentService();
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println(passedStudents);

        Collection<Student> orderedStudents = studentService.getStudentsOrderByScore();
        System.out.println(orderedStudents);

        /* load csv test */
//
//        Students students = CsvStudents.getInstance();
//        students.load();
//        Collection<Student> all = students.findAll();
//        all.forEach(o -> System.out.println(o));
    }
}
