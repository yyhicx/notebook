package com.example.service;

import com.example.entity.Student;
import java.util.List;

public interface StudentService {
  /**
   * 查询全部学员业务
   * @return
   */
  List<Student> findAll();
}
