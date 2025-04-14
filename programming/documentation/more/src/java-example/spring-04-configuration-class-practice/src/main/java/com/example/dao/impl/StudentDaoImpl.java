package com.example.dao.impl;

import com.example.dao.StudentDao;
import com.example.entity.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl implements StudentDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  /**
   * 查询全部学生数据
   * @return
   */
  @Override
  public List<Student> queryAll() {
    String sql = "select id,name,age,gender,class as classes from students;";

    // query 可以返回集合
    // BeanPropertyRowMapper 就是封装好 RowMapper 的实现，要求属性名和列名相同即
    List<Student> studentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));

    return studentList;
  }
}
