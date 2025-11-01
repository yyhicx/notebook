package com.example.mapper;

import com.example.entity.Student;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {

  Student selectStudentById(@Param("id") Integer id);

  List<Student> selectStudentList(@Param("keyword") String keyword);

  int insertStudent(Student students);

  int updateStudent(Student students);

  int deleteStudentById(@Param("id") Integer id);

}
