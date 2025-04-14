package com.example.service.impl;

import com.example.entity.Todo;
import com.example.mapper.TodoMapper;
import com.example.service.TodoService;
import com.example.utils.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

  @Autowired
  private TodoMapper todoMapper;

  @Override
  public PageBean<Todo> findByPage(int pageSize, int currentPage) {
    PageHelper pageHelper = new PageHelper(currentPage, pageSize);
    List<Todo> todos = todoMapper.queryPage();
    PageInfo<Todo> pageInfo = new PageInfo<>(todos);
    PageBean<Todo> pageBean = new PageBean<>(
        pageInfo.getPageSize(), pageInfo.getPageNum(), pageInfo.getTotal().intValue(), pageInfo.getList());

    log.info("Result: {}", pageBean);
    return pageBean;
  }
}
