package com.example.notusespringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.notusespringboot.model.Todo;
import com.example.notusespringboot.repository.TodoRepository;

@Service
public class TodoService {
  private final TodoRepository todoRepository;

  public TodoService(TodoRepository todoRepository) {
    this.todoRepository = todoRepository;
  }

  public List<Todo> getAllTodos() {
    return todoRepository.findAll();
  }

  public Optional<Todo> getTodoById(Long id) {
    return todoRepository.findById(id);
  }

  public Todo saveTodo(Todo todo) {
    return todoRepository.save(todo);
  }

  public void deleteTodoById(Long id) {
    todoRepository.deleteById(id);
  }
}
