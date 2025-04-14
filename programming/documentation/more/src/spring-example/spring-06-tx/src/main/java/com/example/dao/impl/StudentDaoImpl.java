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

  @Override
  public List<Student> queryAll() {
    String sql = "select id,name,age,gender,class as classes from students;";
    List<Student> studentList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
    return studentList;
  }

  public void updateNameById(Integer id, String name) {
    String sql = "update students set name = ? where id = ?;";
    int rows = jdbcTemplate.update(sql, name, id);
  }

  public void updateAgeById(Integer id, Integer age) {
    String sql = "update students set age = ? where id = ?;";
    jdbcTemplate.update(sql, age , id);
  }
}
