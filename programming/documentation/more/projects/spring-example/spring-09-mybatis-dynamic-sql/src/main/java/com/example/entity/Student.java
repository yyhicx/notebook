package com.example.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("student")
@Data
public class Student {
  private Integer id;
  private String name;
  private String gender;
  private Integer age;
  private String classes;

  public Student(Integer id, String name, String gender, Integer age, String classes) {
    this.id = id;
    this.name = name;
    this.gender = gender;
    this.age = age;
    this.classes = classes;
  }
}
