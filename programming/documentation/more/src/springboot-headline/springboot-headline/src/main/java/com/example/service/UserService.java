package com.example.service;

import com.example.entity.User;
import com.example.utils.Result;

public interface UserService {
  Result login(User user);
  Result getUserInfo(String token);
  Result register(User user);
  Result checkUsername(String username);
  Result checkLogin(String token);
}
