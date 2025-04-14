package com.example.service;

import com.example.entity.Todo;
import com.example.utils.PageBean;

public interface TodoService {
  PageBean<Todo> findByPage(int pageSize, int currentPage);
}
