package com.example.utils;

/**
 * 统一返回结果状态信息类
 */
public enum ResultCodeEnum {
  SUCCESS(200, "success"),
  USERNAME_ERROR(501, "username error"),
  PASSWORD_ERROR(503, "password error"),
  NOT_LOGIN(504, "not login"),
  USERNAME_USED(505, "username used"),
  USERNAME_EMPTY(506, "username empty");

  private final Integer code;
  private final String message;

  ResultCodeEnum(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }
}
