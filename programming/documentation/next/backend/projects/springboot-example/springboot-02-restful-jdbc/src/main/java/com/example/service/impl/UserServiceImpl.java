package com.example.service.impl;

import com.example.dto.Page;
import com.example.entity.User;
import com.example.mapper.UserDao;
import com.example.service.UserService;
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
  private UserDao userDao;

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
    if (userDao.existsByUsername(user.getUsername())) {
      throw new RuntimeException("Username already exists");
    }
    if (userDao.existsByEmail(user.getEmail())) {
      throw new RuntimeException("Email already exists");
    }

    // 设置默认值
    user.setStatus(true);
    user.setCreatedTime(LocalDateTime.now());
    user.setUpdatedTime(LocalDateTime.now());
    user.setIsDeleted(false);

    log.info("Creating user: {}", user.getUsername());
    return userDao.createUser(user);
  }

  @Override
  public User updateUser(User user) {
    User existingUser = userDao.getUserById(user.getId())
      .orElseThrow(() -> new RuntimeException("User not found"));

    // 检查用户名和邮箱是否已经存在
    if (user.getUsername() != null &&
      !Objects.equals(existingUser.getUsername(), user.getUsername()) &&
      userDao.existsByUsername(user.getUsername())) {
      throw new RuntimeException("The username has been used by another user");
    }
    if (user.getEmail() != null &&
      !Objects.equals(existingUser.getEmail(), user.getEmail()) &&
      userDao.existsByEmail(user.getEmail())) {
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

    int updated = userDao.updateUser(existingUser);
    if (updated == 0) {
      throw new RuntimeException("Failed to update user");
    }
    return userDao.getUserById(user.getId())
      .orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Override
  public void deleteUserById(Integer id) {
    User existingUser = userDao.getUserById(id)
      .orElseThrow(() -> new RuntimeException("User not found"));

    int deleted = userDao.deleteUserById(id);
    if (deleted == 0) {
      throw new RuntimeException("Failed to delete user");
    }

    log.info("Deleted user: {}", existingUser.getUsername());
  }

  @Override
  @Transactional(readOnly = true)
  public List<User> getAllUsers() {
    return userDao.getAllUsers();
  }

  @Override
  @Transactional(readOnly = true)
  public User getUserById(Integer id) {
    return userDao.getUserById(id)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Override
  @Transactional(readOnly = true)
  public User getUserByUsername(String username) {
    return userDao.getUserByUsername(username)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Override
  @Transactional(readOnly = true)
  public User getUserByEmail(String email) {
    return userDao.getUserByEmail(email)
      .orElseThrow(() -> new RuntimeException("User not found"));
  }

  @Override
  @Transactional(readOnly = true)
  public Page<User> getUsersByPage(int pageNum, int pageSize) {
    Page<User> users = userDao.getUsersByPage(pageNum, pageSize);
    return users;
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsByUsername(String username) {
    return userDao.existsByUsername(username);
  }

  @Override
  @Transactional(readOnly = true)
  public boolean existsByEmail(String email) {
    return userDao.existsByEmail(email);
  }
}
