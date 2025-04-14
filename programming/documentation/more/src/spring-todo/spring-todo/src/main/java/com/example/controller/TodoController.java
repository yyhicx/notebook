package com.example.controller;

import com.example.entity.Todo;
import com.example.service.TodoService;
import com.example.utils.PageBean;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "todo")
@CrossOrigin(origins = "*")  // 支持跨域请求
public class TodoController {

  @Autowired
  private TodoService todoService;

  @GetMapping(value = "{pageSize}/{currentPage}")
  public R getTodoList(@PathVariable("pageSize") int pageSize,
                       @PathVariable("currentPage") int currentPage) {
    PageBean<Todo> pageBean = todoService.findByPage(pageSize, currentPage);
    return R.ok("Data query successful", pageBean);
  }
}
