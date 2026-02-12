package com.example.service.impl;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {

  @Autowired
  private UserMapper userMapper;

  @Override
  public User createUser(User user) {
    // 参数校验
    if (user == null) {
      throw new IllegalArgumentException("User must not be null");
    }
    if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
      throw new IllegalArgumentException("Username must not be empty");
    }
    if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
      throw new IllegalArgumentException("Password must not be empty");
    }
    if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
      throw new IllegalArgumentException("Email must not be empty");
    }

    // 检查用户名和邮箱是否已经存在
    if (userMapper.existsByUsername(user.getUsername())) {
      throw new RuntimeException("Username already exists");
    }
    if (userMapper.existsByEmail(user.getEmail())) {
      throw new RuntimeException("Email already exists");
    }

    // 设置默认值
    user.setStatus(true);
    user.setCreatedTime(LocalDateTime.now());
    user.setUpdatedTime(LocalDateTime.now());
    user.setIsDeleted(false);

    log.info("Creating user: {}", user.getUsername());
    int created = userMapper.createUser(user);
    if (created > 0) {
      User insertedUser = userMapper.getUserById(user.getId());
      if (insertedUser == null) {
        throw new RuntimeException("Created but cannot fetch created user");
      }
      return insertedUser;
    } else {
      throw new RuntimeException("Failed to create user");
    }
  }

  @Override
  public User updateUser(User user) {
    User existingUser = userMapper.getUserById(user.getId());
    if (existingUser == null) {
      throw new RuntimeException("User not found");
    }

    // 检查用户名和邮箱是否已经存在
    if (user.getUsername() != null &&
      !Objects.equals(existingUser.getUsername(), user.getUsername()) &&
      userMapper.existsByUsername(user.getUsername())) {
      throw new RuntimeException("The username has been used by another user");
    }
    if (user.getEmail() != null &&
      !Objects.equals(existingUser.getEmail(), user.getEmail()) &&
      userMapper.existsByEmail(user.getEmail())) {
      throw new RuntimeException("The email has been used by another user");
    }

    // 更新允许修改的字段
    if (user.getUsername() != null) {
      existingUser.setUsername(user.getUsername());
    }
    if (user.getPassword() != null) {
      existingUser.setPassword(user.getPassword());
    }
    if (user.getEmail() != null) {
      existingUser.setEmail(user.getEmail());
    }
    if (user.getPhone() != null) {
      existingUser.setPhone(user.getPhone());
    }
    if (user.getStatus() != null) {
      existingUser.setStatus(user.getStatus());
    }
    existingUser.setUpdatedTime(LocalDateTime.now());

    int updated = userMapper.updateUser(existingUser);
    if (updated == 0) {
      throw new RuntimeException("Failed to update user");
    }
    User refreshedUser = userMapper.getUserById(user.getId());
    if (refreshedUser == null) {
      throw new RuntimeException("User not found after update");
    }
    return refreshedUser;
  }

  @Override
  public void deleteUserById(Integer id) {
    User existingUser = userMapper.getUserById(id);
    if (existingUser == null) {
      throw new RuntimeException("User not found");
    }

    int deleted = userMapper.deleteUserById(id);
    if (deleted == 0) {
      throw new RuntimeException("Failed to delete user");
    }

    log.info("Deleted user: {}", existingUser.getUsername());
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> getAllUsers() {
    return userMapper.getAllUsers();
  }

  @Override
  @Transactional(readOnly = true)
  public User getUserById(Integer id) {
    User user = userMapper.getUserById(id);
    if (user == null) {
      throw new RuntimeException("User not found");
    }
    return user;
  }

  @Override
  @Transactional(readOnly = true)
  public User getUserByUsername(String username) {
    User user = userMapper.getUserByUsername(username);
    if (user == null) {
      throw new RuntimeException("User not found");
    }
    return user;
  }

  @Override
  @Transactional(readOnly = true)
  public User getUserByEmail(String email) {
    User user = userMapper.getUserByEmail(email);
    if (user == null) {
      throw new RuntimeException("User not found");
    }
    return user;
  }

  @Override
  @Transactional(readOnly = true)
  public PageInfo<User> getUsersByPage(int pageNum, int pageSize) {
    PageHelper.startPage(pageNum, pageSize);

    List<User> users = userMapper.getAllUsers();

    PageInfo<User> pageInfo = new PageInfo<>(users);
    return pageInfo;
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsByUsername(String username) {
    return userMapper.existsByUsername(username);
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsByEmail(String email) {
    return userMapper.existsByEmail(email);
  }
}
