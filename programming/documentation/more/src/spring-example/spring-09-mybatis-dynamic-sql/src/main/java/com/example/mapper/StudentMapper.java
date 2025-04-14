package com.example.mapper;

import com.example.entity.Student;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
  List<Student> selectStudentList(@Param("gender") String gender, @Param("age") Integer age);

  int updateStudentById(@Param("id") Integer id, @Param("name") String name, @Param("age") Integer age);

  List<Student> selectStudentListTrim(@Param("gender") String gender, @Param("age") Integer age);

  int updateStudentByIdTrim(@Param("id") Integer id, @Param("name") String name, @Param("age") Integer age);

  List<Student> selectStudentListChoose(@Param("gender") String gender, @Param("age") Integer age);

  List<Student> selectBatch(@Param("ids") List<Integer> ids);

  int insertBatch(@Param("students") List<Student> students);

  int updateBatch(@Param("students") List<Student> students);

  int deleteBatch(@Param("ids") List<Integer> ids);
}
