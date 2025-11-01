package com.example.service;

import com.example.entity.Student;
import java.util.List;

public interface StudentService {
  public Student findStudentById(Integer id);
  public List<Student> findStudentList();
}
