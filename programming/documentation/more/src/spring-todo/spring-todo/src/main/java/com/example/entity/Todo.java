package com.example.entity;

import lombok.Data;

@Data
public class Todo {
  private Integer id;
  private String description;
  private Boolean completed;
}
