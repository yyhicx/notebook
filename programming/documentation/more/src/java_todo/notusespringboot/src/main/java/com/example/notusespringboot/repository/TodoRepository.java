package com.example.notusespringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.notusespringboot.model.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
}
