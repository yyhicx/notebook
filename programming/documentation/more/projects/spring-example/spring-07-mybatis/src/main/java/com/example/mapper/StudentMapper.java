package com.example.mapper;

import com.example.entity.Student;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

/**
 * 接口只规定方法、参数和返回值
 * mapper.xml 中编写具体 SQL 语句
 */
public interface StudentMapper {
  // 类型：
  //   简单类型：
  //     简单数据类型：int、byte、short、double...
  //     包装类数据类型：Integer、Byte、Short、Double...
  //     字符串类型：String
  //   复杂类型：
  //     实体类类型：Student、User...
  //     集合类型：List、Set、Map...
  //     数组类型：int[]、String[]...
  //     复合类型：List<Student>...

  // 数据输入：单个简单类型参数
  // 数据输出：单个实体类类型
  Student selectStudentById(Integer id);

  // 数据输入：实体类类型参数
  int insertStudent(Student student);

  // 数据输入：零散的多个简单类型参数，必须使用 @Param 注解标注参数名
  int updateStudent(@Param("id") Integer id, @Param("name") String name, @Param("gender") String gender,
    @Param("age") Integer age, @Param("classes") String classes);

  // 数据输入：Map 类型参数
  int updateStudentByMap(Map<String, Object> map);

  // 数据输出：单个简单类型
  int selectStudentCount();

  // 数据输出：Map 类型
  // 当 SQL 查询返回的各个字段综合起来并不和任何一个现有的实体类对应时，可以使用 Map 类型作为返回值
  // 返回值：年龄最大的学生的 id、姓名和年龄，以及平均年龄（可能含有多个）
  @MapKey("id")
  Map<Integer, Object> selectStudentNameAndMaxAge();

  // 数据输出：List 类型
  // 查询结果返回多个实体类对象，希望把多个实体类对象放在 List 集合中返回
  List<Student> selectAll();

  // 数据输入：单个简单类型参数
  // 数据输出：单个简单类型
  int deleteStudentByName(String name);
}
