package com.example.usespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.usespringboot.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
