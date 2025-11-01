package com.example.mapper;

import com.example.entity.Todo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TodoMapper {

  List<Todo> queryPage();

  void insert(Todo todo);

  void update(Todo todo);

  void delete(Integer id);

}
