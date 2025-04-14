package com.example.mapper;

import com.example.entity.Student;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StudentMapper {
  Student selectStudentById(Integer id);
  List<Student> selectStudentList();
}
