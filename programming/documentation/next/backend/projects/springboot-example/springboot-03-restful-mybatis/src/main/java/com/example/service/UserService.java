package com.example.service;

import com.example.entity.User;
import com.github.pagehelper.PageInfo;
import java.util.List;

public interface UserService {

  /**
   * 创建用户
   */
  User createUser(User user);

  /**
   * 更新用户
   */
  User updateUser(User user);

  /**
   * 根据 ID 删除用户
   */
  void deleteUserById(Integer id);

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
   * 分页查询
   */
  PageInfo<User> getUsersByPage(int pageNum, int pageSize);

  /**
   * 检查用户名是否存在
   */
  boolean existsByUsername(String username);

  /**
   * 检查邮箱是否存在
   */
  boolean existsByEmail(String email);
}
