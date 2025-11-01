package com.example.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.dto.UserDTO;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.example.utils.JwtHelper;
import com.example.utils.MD5Util;
import com.example.utils.Result;
import com.example.utils.ResultCodeEnum;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  private final JwtHelper jwtHelper;
  private final UserMapper userMapper;

  @Autowired
  public UserServiceImpl(JwtHelper jwtHelper, UserMapper userMapper) {
    this.jwtHelper = jwtHelper;
    this.userMapper = userMapper;
  }

  @Override
  public Result login(User user) {
    // 根据账号查询
    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
    User loginUser = userMapper.selectOne(lambdaQueryWrapper);

    // 账号判断
    if (loginUser == null) {
      // 账号不存在
      return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
    }

    // 判断密码
    if (!StringUtils.isEmpty(user.getPassword())
      && loginUser.getPassword().equals(MD5Util.encrypt(user.getPassword()))) {
      // 账号密码正确
      // 生成 token
      String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
      Map<String, Object> data = new HashMap<>();
      data.put("token", token);
      return Result.ok(data);
    }

    // 密码错误
    return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
  }

  @Override
  public Result getUserInfo(String token) {
    // 判断是否有效，结果 true 表示有效
    if (jwtHelper.isExpiration(token)) {
      // 直接返回未登录
      return Result.build(null, ResultCodeEnum.NOT_LOGIN);
    }

    // 根据 token 获取用户信息
    int userId = jwtHelper.getUserId(token).intValue();
    User user = userMapper.selectById(userId);

    if (user != null) {
      // 用户存在
      UserDTO userDTO = new UserDTO(user.getUid(), user.getUsername(), user.getNickName());
      Map<String, Object> data = new HashMap<>();
      data.put("loginUser", userDTO);
      return Result.ok(data);
    }

    // 用户不存在
    return Result.build(null, ResultCodeEnum.NOT_LOGIN);
  }

  @Override
  public Result register(User user) {
    // 判断账号是否存在
    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
    Long count = userMapper.selectCount(lambdaQueryWrapper);

    if (count > 0) {
      // 账号已存在
      return Result.build(null, ResultCodeEnum.USERNAME_USED);
    }

    // 保存用户信息
    user.setPassword(MD5Util.encrypt(user.getPassword()));
    userMapper.insert(user);

    // 注册成功
    return Result.ok(null);
  }

  @Override
  public Result checkUsername(String username) {
    if (StringUtils.isEmpty(username)) {
      return Result.build(null, ResultCodeEnum.USERNAME_EMPTY);
    }

    // 判断账号是否存在
    LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
    lambdaQueryWrapper.eq(User::getUsername, username);
    User user = userMapper.selectOne(lambdaQueryWrapper);

    if (user != null) {
      // 账号已存在
      return Result.build(null, ResultCodeEnum.USERNAME_USED);
    }

    // 账号不存在
    return Result.ok(null);
  }

  @Override
  public Result checkLogin(String token) {
    if (!StringUtils.isEmpty(token) && !jwtHelper.isExpiration(token)) {
      return Result.ok(null);
    }

    return Result.build(null, ResultCodeEnum.NOT_LOGIN);
  }
}
