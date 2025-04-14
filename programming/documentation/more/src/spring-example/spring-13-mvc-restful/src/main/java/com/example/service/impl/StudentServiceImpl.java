package com.example.service.impl;

import com.example.entity.Student;
import com.example.mapper.StudentMapper;
import com.example.service.StudentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
  public Student findById(Integer id) {
    return studentMapper.selectStudentById(id);
  }

  @Override
  public List<Student> findAll(String keyword) {
    return studentMapper.selectStudentList(keyword);
  }

  @Override
  public List<Student> findAllWithPageable(String keyword, int page, int size) {
    PageHelper.startPage(page, size);
    List<Student> students = studentMapper.selectStudentList(keyword);
    PageInfo<Student> pageInfo = new PageInfo<>(students);
    return pageInfo.getList();
  }

  @Override
  public boolean save(Student student) {
    int result = studentMapper.insertStudent(student);
    return result > 0;
  }

  @Override
  public boolean update(Student student) {
    int result = studentMapper.updateStudent(student);
    return result > 0;
  }

  @Override
  public boolean deleteById(Integer id) {
    int result = studentMapper.deleteStudentById(id);
    return result > 0;
  }

}
