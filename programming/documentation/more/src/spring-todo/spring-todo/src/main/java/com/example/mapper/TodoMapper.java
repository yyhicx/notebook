package com.example.mapper;

import com.example.entity.Todo;
import java.util.List;

public interface TodoMapper {

  List<Todo> queryPage();

}
