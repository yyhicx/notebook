package com.example.controller;

import com.example.dto.Page;
import com.example.dto.Result;
import com.example.entity.User;
import com.example.service.UserService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping
  public Result<User> createUser(@RequestBody User user) {
    try {
      User createdUser = userService.createUser(user);
      return Result.success("Created user successfully", createdUser);
    } catch (RuntimeException e) {
      log.error("createUser failed", e);
      return Result.error(e.getMessage() != null ? e.getMessage() : "Failed to create user");
    }
  }

  @PutMapping("/{id}")
  public Result<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
    try {
      user.setId(id);
      User updatedUser = userService.updateUser(user);
      return Result.success("Updated user successfully", updatedUser);
    } catch (RuntimeException e) {
      log.error("updateUser failed, id={}", id, e);
      return Result.error(e.getMessage() != null ? e.getMessage() : "Failed to update user");
    }
  }

  @DeleteMapping("/{id}")
  public Result<Void> deleteUser(@PathVariable Integer id) {
    try {
      userService.deleteUserById(id);
      return Result.success("Deleted user successfully");
    } catch (RuntimeException e) {
      log.error("deleteUser failed, id={}", id, e);
      return Result.error(e.getMessage() != null ? e.getMessage() : "Failed to delete user");
    }
  }

  @GetMapping
  public Result<List<User>> getAllUsers() {
    try {
      List<User> users = userService.getAllUsers();
      return Result.success("Get all users successfully", users);
    } catch (RuntimeException e) {
      log.error("getAllUsers failed", e);
      return Result.error(e.getMessage() != null ? e.getMessage() : "Failed to get all users");
    }
  }

  @GetMapping("/{id}")
  public Result<User> getUserById(@PathVariable Integer id) {
    try {
      User user = userService.getUserById(id);
      return Result.success("Get user by id successfully", user);
    } catch (RuntimeException e) {
      log.error("getUserById failed, id={}", id, e);
      return Result.error(e.getMessage() != null ? e.getMessage() : "Failed to get user by id");
    }
  }

  @GetMapping("/username/{username}")
  public Result<User> getUserByUsername(@PathVariable String username) {
    try {
      User user = userService.getUserByUsername(username);
      return Result.success("Get user by username successfully", user);
    } catch (RuntimeException e) {
      log.error("getUserByUsername failed, username={}", username, e);
      return Result.error(
        e.getMessage() != null ? e.getMessage() : "Failed to get user by username");
    }
  }

  @GetMapping("/email/{email}")
  public Result<User> getUserByEmail(@PathVariable String email) {
    try {
      User user = userService.getUserByEmail(email);
      return Result.success("Get user by email successfully", user);
    } catch (RuntimeException e) {
      log.error("getUserByEmail failed, email={}", email, e);
      return Result.error(e.getMessage() != null ? e.getMessage() : "Failed to get user by email");
    }
  }

  @GetMapping("/page")
  public Result<Page<User>> getUsersByPage(
    @RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
    @RequestParam(value = "pageSize", defaultValue = "10") int pageSize
  ) {
    try {
      Page<User> pageInfo = userService.getUsersByPage(pageNum, pageSize);
      return Result.success("Get users by page successfully", pageInfo);
    } catch (RuntimeException e) {
      log.error("getUsersByPage failed, pageNum={}, pageSize={}", pageNum, pageSize, e);
      return Result.error(e.getMessage() != null ? e.getMessage() : "Failed to get users by page");
    }
  }
}
