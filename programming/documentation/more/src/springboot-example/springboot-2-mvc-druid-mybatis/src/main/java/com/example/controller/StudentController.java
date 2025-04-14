package com.example.controller;

import com.example.entity.Student;
import com.example.service.StudentService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "student")
@Slf4j
public class StudentController {
  @Autowired
  private StudentService studentService;

  @GetMapping(value = "{id}")
  public Student getStudentById(@PathVariable("id") Integer id) {
    Student student = studentService.findStudentById(id);
    log.info("Student Info: {}", student);
    return student;
  }

  @GetMapping
  public List<Student> getStudentList() {
    List<Student> studentList = studentService.findStudentList();
    log.info("Student List: {}", studentList);
    return studentList;
  }
}
