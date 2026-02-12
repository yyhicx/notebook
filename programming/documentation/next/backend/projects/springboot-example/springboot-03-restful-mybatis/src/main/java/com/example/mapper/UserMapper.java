package com.example.mapper;

import com.example.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  /**
   * 创建用户
   */
  int createUser(User user);

  /**
   * 更新用户
   */
  int updateUser(User user);

  /**
   * 根据 ID 删除用户
   */
  int deleteUserById(Integer id);

  /**
   * 查询所有用户
   */
  List<User> getAllUsers();

  /**
   * 根据 ID 查询用户
   */
  User getUserById(Integer id);

  /**
   * 根据用户名查询用户
   */
  User getUserByUsername(String username);

  /**
   * 根据邮箱查询用户
   */
  User getUserByEmail(String email);

  /**
   * 检查用户名是否存在
   */
  boolean existsByUsername(String username);

  /**
   * 检查邮箱是否存在
   */
  boolean existsByEmail(String email);
}
