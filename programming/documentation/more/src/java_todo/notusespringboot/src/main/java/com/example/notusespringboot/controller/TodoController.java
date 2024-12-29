package com.example.notusespringboot.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.notusespringboot.model.Todo;
import com.example.notusespringboot.service.TodoService;

@Controller
@RequestMapping("/todos")
public class TodoController {
  private final TodoService todoService;

  public TodoController(TodoService todoService) {
    this.todoService = todoService;
  }

  @GetMapping()
  public String getAllTodos(Model model) {
    model.addAttribute("todos", todoService.getAllTodos());
    return "index";
  }

  @GetMapping("/create")
  public String createTodoForm(Model model) {
    model.addAttribute("todo", new Todo());
    return "create";
  }

  @PostMapping("/create")
  public String createTodo(@ModelAttribute Todo todo) {
    todoService.saveTodo(todo);
    return "redirect:/todos";
  }

  @GetMapping("/update/{id}")
  public String updateTodoForm(@PathVariable Long id, Model model) {
    Optional<Todo> todo = todoService.getTodoById(id);
    if (todo.isPresent()) {
      model.addAttribute("todo", todo.get());
      return "update";
    }
    return "redirect:/todos";
  }

  @PostMapping("/update")
  public String updateTodo(@ModelAttribute Todo todo) {
    todoService.saveTodo(todo);
    return "redirect:/todos";
  }

  @GetMapping("/delete/{id}")
  public String deleteTodo(@PathVariable Long id) {
    todoService.deleteTodoById(id);
    return "redirect:/todos";
  }
}
