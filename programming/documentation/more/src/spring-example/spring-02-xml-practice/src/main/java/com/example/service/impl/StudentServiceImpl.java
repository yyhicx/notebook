package com.example.service.impl;

import com.example.dao.StudentDao;
import com.example.entity.Student;
import com.example.service.StudentService;
import java.util.List;

public class StudentServiceImpl implements StudentService {

  private StudentDao studentDao;

  public void setStudentDao(StudentDao studentDao) {
    this.studentDao = studentDao;
  }

  /**
   * 查询全部学员业务
   * @return
   */
  @Override
  public List<Student> findAll() {
    List<Student> studentList =  studentDao.queryAll();
    return studentList;
  }
}
