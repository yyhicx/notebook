package com.example.service;

import com.example.entity.Student;
import java.util.List;

public interface StudentService {
  List<Student> findAll();

  void changeInfo(Integer id, String name, Integer age);
}
