package com.example.utils;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局统一返回结果类
 */
@Data
@NoArgsConstructor
public class Result<T> {
  private Integer code;  // 返回码
  private String message;  // 返回消息
  private T data;  // 返回数据

  protected static <T> Result<T> build(T data) {
    Result<T> result = new Result<>();
    if (data != null) result.setData(data);
    return result;
  }

  public static <T> Result<T> build(T body, Integer code, String message) {
    Result<T> result = build(body);
    result.setCode(code);
    result.setMessage(message);
    return result;
  }

  public static <T> Result<T> build(T body, ResultCodeEnum resultCodeEnum) {
    Result<T> result = build(body);
    result.setCode(resultCodeEnum.getCode());
    result.setMessage(resultCodeEnum.getMessage());
    return result;
  }

  /**
   * 操作成功
   */
  public static <T> Result<T> ok(T data) {
    return build(data, ResultCodeEnum.SUCCESS);
  }
}
