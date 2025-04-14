package com.example.utils;

import lombok.Data;

@Data
public class R {
  private int code;  // 状态码
  private String msg;  // 提示信息
  private Object data;  // 具体数据

  public static R ok(String msg, Object data){
    R r = new R();
    r.code = 200;
    r.msg = msg;
    r.data = data;
    return r;
  }

  public static R fail(String msg, Object data){
    R r = new R();
    r.code = 500;
    r.msg = msg;
    r.data = data;
    return r;
  }
}
