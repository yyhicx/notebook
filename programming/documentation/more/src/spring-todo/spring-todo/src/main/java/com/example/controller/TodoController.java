package com.example.controller;

import com.example.entity.Todo;
import com.example.service.TodoService;
import com.example.utils.PageBean;
import com.example.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping
  public R addTodo(@RequestBody Todo todo) {
    todoService.saveTodo(todo);
    return R.ok("Data saved successfully", null);
  }

  @PutMapping
  public R changeTodo(@RequestBody Todo todo) {
    todoService.updateTodo(todo);
    return R.ok("Data modification successful", null);
  }

  @DeleteMapping(value = "{id}")
  public R removeTodo(@PathVariable("id") Integer id) {
    todoService.removeTodoById(id);
    return R.ok("Data deletion successful", null);
  }
}
