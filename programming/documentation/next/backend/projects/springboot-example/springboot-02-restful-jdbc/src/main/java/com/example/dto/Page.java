package com.example.dto;

import java.util.List;
import lombok.Data;

@Data
public class Page<T> {

  private int pageNum;

  private int pageSize;

  private long total;

  private List<T> list;

  private int pages;
}
