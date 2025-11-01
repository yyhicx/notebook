package com.example.dao;

import com.example.entity.Student;
import java.util.List;

public interface StudentDao {
  List<Student> queryAll();

  void updateNameById(Integer id, String name);

  void updateAgeById(Integer id, Integer age);
}

