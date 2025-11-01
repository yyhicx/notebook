package com.example.service.impl;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {
  @Autowired
  private StudentMapper studentMapper;

  @Override
  public Student findStudentById(Integer id) {
    return studentMapper.selectStudentById(id);
  }

  @Override
  public List<Student> findStudentList() {
    return studentMapper.selectStudentList();
  }
}
