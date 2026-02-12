package com.example.dto;

import lombok.Data;

@Data
public class Result<T> {

  /**
   * 状态码
   */
  private Integer code;

  /**
   * 响应消息
   */
  private String message;

  /**
   * 响应数据
   */
  private T data;

  /**
   * 私有构造方法
   */
  private Result(Integer code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  /**
   * 成功响应（无数据）
   */
  public static <T> Result<T> success() {
    return new Result<>(200, "success", null);
  }

  /**
   * 成功响应（带数据）
   */
  public static <T> Result<T> success(String message) {
    return new Result<>(200, message, null);
  }

  /**
   * 成功响应（带数据和自定义消息）
   */
  public static <T> Result<T> success(String message, T data) {
    return new Result<>(200, message, data);
  }

  /**
   * 成功响应（自定义状态码、消息和数据）
   */
  public static <T> Result<T> success(Integer code, String message, T data) {
    return new Result<>(code, message, data);
  }

  /**
   * 错误响应（默认消息）
   */
  public static <T> Result<T> error() {
    return new Result<>(500, "error", null);
  }

  /**
   * 错误响应（自定义消息）
   */
  public static <T> Result<T> error(String message) {
    return new Result<>(500, message, null);
  }

  /**
   * 错误响应（自定义状态码和消息）
   */
  public static <T> Result<T> error(Integer code, String message) {
    return new Result<>(code, message, null);
  }

  /**
   * 错误响应（自定义状态码、消息和数据）
   */
  public static <T> Result<T> error(Integer code, String message, T data) {
    return new Result<>(code, message, data);
  }
}
