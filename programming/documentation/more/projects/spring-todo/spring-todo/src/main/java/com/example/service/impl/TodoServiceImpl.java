package com.example.service.impl;

import com.example.entity.Todo;
import com.example.mapper.TodoMapper;
import com.example.service.TodoService;
import com.example.utils.PageBean;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

  private final static Log logger = LogFactory.getLog(TodoServiceImpl.class);

  @Autowired
  private TodoMapper todoMapper;

  @Override
  public PageBean<Todo> findByPage(int pageSize, int currentPage) {
    PageHelper.startPage(currentPage, pageSize);
    List<Todo> todos = todoMapper.queryPage();
    PageInfo<Todo> pageInfo = new PageInfo<>(todos);
    PageBean<Todo> pageBean = new PageBean<>(
        pageInfo.getPageSize(), pageInfo.getPageNum(), (int)pageInfo.getTotal(), pageInfo.getList());

    logger.info("Result: " + pageBean);
    return pageBean;
  }

  @Override
  public void saveTodo(Todo todo) {
    todoMapper.insert(todo);
  }

  @Override
  public void updateTodo(Todo todo) {
    todoMapper.update(todo);
  }

  @Override
  public void removeTodoById(Integer id) {
    todoMapper.delete(id);
  }
}
