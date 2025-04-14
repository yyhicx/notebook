package com.example.controller;

import com.example.entity.Student;
import com.example.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {
  @Autowired
  private StudentService studentService;

  public void findAll() {
    List<Student> studentList = studentService.findAll();
    System.out.println("studentList = " + studentList);
  }

  public void changeInfo(Integer id, String name, Integer age) {
    studentService.changeInfo(id, name, age);
  }
}
