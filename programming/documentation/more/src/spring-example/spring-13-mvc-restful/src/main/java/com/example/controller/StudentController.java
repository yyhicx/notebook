package com.example.controller;

import com.example.entity.Student;
import com.example.service.StudentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "student", consumes = "application/json;charset=UTF-8", produces = "application/json;charset=UTF-8")
public class StudentController {

  @Autowired
  private StudentService studentService;

  /**
   * 用户详情：GET /student/{id}
   */
  @GetMapping(value = "{id}")
  public String getStudentById(@PathVariable("id") Integer id) {
    Student student = studentService.findById(id);
    if (student == null) {
      return "Student not found";
    }
    return student.toString();
  }


  /**
   * 分页查询：GET /student?page=0&size=10
   */
  @GetMapping
  public String getAllStudentsWithPageable(
      @RequestParam(value = "page", required = false, defaultValue = "1") int page,
      @RequestParam(value = "size", required = false, defaultValue = "10") int size
  ) {
    List<Student> students = studentService.findAllWithPageable(null, page, size);
    return students.toString();
  }

  /**
   * 条件模糊查询：GET /student/search
   */
  @GetMapping(value = "search")
  public String getAllStudentsWithKeyword(
    @RequestParam(value = "keyword", required = false) String keyword,
    @RequestParam(value = "page", required = false, defaultValue = "1") int page,
    @RequestParam(value = "size", required = false, defaultValue = "10") int size
  ) {
    List<Student> students = studentService.findAllWithPageable(keyword, page, size);
    return students.toString();
  }

  /**
   * 用户添加：POST /student
   */
  @PostMapping
  public String addStudent(@RequestBody Student student) {
    if (studentService.save(student)) {
      return student.toString();
    } else {
      return "Failed to add student";
    }
  }

  /**
   * 用户更新：PUT /student
   */
  @PutMapping
  public String updateStudent(@RequestBody Student student) {
    if (studentService.update(student)) {
      return student.toString();
    } else {
      return "Failed to update student";
    }
  }

  /**
   * 用户删除：DELETE /student/{id}
   */
  @DeleteMapping(value = "{id}")
  public String deleteStudentById(@PathVariable("id") Integer id) {
    if (studentService.deleteById(id)) {
      return "Student deleted successfully";
    } else {
      return "Failed to delete student";
    }
  }

}
