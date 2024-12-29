package com.example.usespringboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.usespringboot.model.Todo;
import com.example.usespringboot.repository.TodoRepository;

@Service
public class TodoService {
  @Autowired
  private TodoRepository todoRepository;

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
