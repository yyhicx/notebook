package com.example.service.impl;

import com.example.dao.StudentDao;
import com.example.entity.Student;
import com.example.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
  @Autowired
  private StudentDao studentDao;

  @Override
  public List<Student> findAll() {
    List<Student> studentList =  studentDao.queryAll();
    return studentList;
  }

  @Override
  @Transactional
  public void changeInfo(Integer id, String name, Integer age) {
    System.out.println("Update student name");
    studentDao.updateNameById(id, name);
    System.out.println("Update student age");
    studentDao.updateAgeById(id, age);
  }
}
