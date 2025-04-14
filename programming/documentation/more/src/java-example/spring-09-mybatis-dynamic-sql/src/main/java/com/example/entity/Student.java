package com.example;

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
}
