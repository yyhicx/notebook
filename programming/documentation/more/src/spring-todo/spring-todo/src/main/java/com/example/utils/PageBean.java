package com.example.utils;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor  // 无参构造
@AllArgsConstructor  // 全参构造
public class PageBean<T> {
  private int pageSize;
  private int currentPage;
  private int total;
  private List<T> todos;
}
