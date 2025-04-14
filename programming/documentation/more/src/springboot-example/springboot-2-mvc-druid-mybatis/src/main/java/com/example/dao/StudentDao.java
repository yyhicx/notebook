package com.example.dao;

import com.example.entity.Student;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao {
  @Autowired
  private JdbcTemplate jdbcTemplate;

  public Student selectStudentById(Integer id) {
    String sql = "select * from students where id = ?";
    return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Student.class), id);
  }

  public List<Student> selectStudentList() {
    String sql = "select * from students";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Student.class));
  }
}
