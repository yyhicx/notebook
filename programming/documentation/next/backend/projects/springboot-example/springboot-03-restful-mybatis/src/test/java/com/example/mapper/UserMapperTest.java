package com.example.mapper;

import com.example.SpringBoot03RestfulMyBatisApplication;
import com.example.entity.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBoot03RestfulMyBatisApplication.class)
public class UserMapperTest {

  @Autowired
  private UserMapper userMapper;

  @Test
  public void testCreateUser() {
    User user = new User();
    user.setUsername("test");
    user.setPassword("123456");
    user.setEmail("test@localhost");
    user.setPhone("13312347890");
    user.setStatus(true);
    user.setCreatedTime(LocalDateTime.now());
    user.setUpdatedTime(LocalDateTime.now());
    user.setIsDeleted(false);
    int createdCount = userMapper.createUser(user);
    System.out.println(createdCount);
  }

  @Test
  public void testUpdateUser() {
    User user = userMapper.getUserById(4);
    if (user == null) {
      new RuntimeException("User not found");
    }
    user.setUsername("test123");
    int updatedCount = userMapper.updateUser(user);
    System.out.println(updatedCount);
  }

  @Test
  public void testDeleteUserById() {
    int deletedCount = userMapper.deleteUserById(4);
    System.out.println(deletedCount);
  }

  @Test
  public void testGetAllUsers() {
    List<User> allUsers = userMapper.getAllUsers();
    System.out.println(allUsers);
  }

  @Test
  public void testGetUserById() {
    User user = userMapper.getUserById(3);
    if (user == null) {
      new RuntimeException("User not found");
    }
    System.out.println(user);
  }

  @Test
  public void testGetUserByUsername() {
    User user = userMapper.getUserByUsername("user2");
    if (user == null) {
      new RuntimeException("User not found");
    }
    System.out.println(user);
  }

  @Test
  public void testGetUserByEmail() {
    User user = userMapper.getUserByEmail("user2@localhost");
    if (user == null) {
      new RuntimeException("User not found");
    }
    System.out.println(user);
  }

  @Test
  public void testGetUsersByPage() {
    PageHelper.startPage(1, 10);
    PageInfo<User> pageInfo = new PageInfo<>(userMapper.getAllUsers());
    System.out.println(pageInfo.getList());
  }

  @Test
  public void testExistsByUsername() {
    boolean exists = userMapper.existsByUsername("test");
    System.out.println(exists);
  }

  @Test
  public void testExistsByEmail() {
    boolean exists = userMapper.existsByEmail("test@localhost");
    System.out.println(exists);
  }
}
