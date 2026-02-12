package com.example.entity;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class User {
  
  private Integer id;

  private String username;

  private String password;

  private String email;

  private String phone;

  private Boolean status;

  private LocalDateTime createdTime;

  private LocalDateTime updatedTime;

  private Boolean isDeleted;
}
