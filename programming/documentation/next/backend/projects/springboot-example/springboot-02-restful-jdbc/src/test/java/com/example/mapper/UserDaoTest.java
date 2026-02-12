package com.example.mapper;

import com.example.SpringBoot02RestfulJDBCApplication;
import com.example.dto.Page;
import com.example.entity.User;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SpringBoot02RestfulJDBCApplication.class)
public class UserDaoTest {

  @Autowired
  private UserDao userDao;

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
    User createdUser = userDao.createUser(user);
    System.out.println(createdUser);
  }

  @Test
  public void testUpdateUser() {
    User user = userDao.getUserById(4).orElseThrow(RuntimeException::new);
    user.setUsername("test123");
    int updatedCount = userDao.updateUser(user);
    System.out.println(updatedCount);
  }

  @Test
  public void testDeleteUserById() {
    int deletedCount = userDao.deleteUserById(4);
    System.out.println(deletedCount);
  }

  @Test
  public void testGetAllUsers() {
    List<User> allUsers = userDao.getAllUsers();
    System.out.println(allUsers);
  }

  @Test
  public void testGetUserById() {
    User user = userDao.getUserById(3).orElseThrow(RuntimeException::new);
    System.out.println(user);
  }

  @Test
  public void testGetUserByUsername() {
    User user = userDao.getUserByUsername("user2").orElseThrow(RuntimeException::new);
    System.out.println(user);
  }

  @Test
  public void testGetUserByEmail() {
    User user = userDao.getUserByEmail("user2@localhost").orElseThrow(RuntimeException::new);
    System.out.println(user);
  }

  @Test
  public void testGetUsersByPage() {
    Page<User> pageInfo = userDao.getUsersByPage(1, 10);
    System.out.println(pageInfo);
  }

  @Test
  public void testExistsByUsername() {
    boolean exists = userDao.existsByUsername("test");
    System.out.println(exists);
  }

  @Test
  public void testExistsByEmail() {
    boolean exists = userDao.existsByEmail("test@localhost");
    System.out.println(exists);
  }
}
