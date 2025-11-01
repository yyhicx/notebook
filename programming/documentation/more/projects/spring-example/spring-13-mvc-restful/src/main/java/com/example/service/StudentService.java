package com.example.service;

import com.example.entity.Student;
import java.util.List;

public interface StudentService {
  Student findById(Integer id);

  List<Student> findAll(String keyword);

  List<Student> findAllWithPageable(String keyword, int page, int size);

  boolean save(Student student);

  boolean update(Student student);

  boolean deleteById(Integer id);
}
