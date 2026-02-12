package com.example.dto;

public enum ResultCode {

  // 成功状态码
  SUCCESS(200, "Success"),
  CREATED(201, "Created"),
  ACCEPTED(202, "Accepted"),
  NO_CONTENT(204, "No Content"),

  // 客户端错误状态码
  BAD_REQUEST(400, "Bad Request"),
  UNAUTHORIZED(401, "Unauthorized"),
  FORBIDDEN(403, "Forbidden"),
  NOT_FOUND(404, "Not Found"),
  METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
  NOT_ACCEPTABLE(406, "Not Acceptable"),
  CONFLICT(409, "Conflict"),
  UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),

  // 服务器错误状态码
  INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
  NOT_IMPLEMENTED(501, "Not Implemented"),
  BAD_GATEWAY(502, "Bad Gateway"),
  SERVICE_UNAVAILABLE(503, "Service Unavailable"),
  GATEWAY_TIMEOUT(504, "Gateway Timeout");

  private final int code;
  private final String message;

  ResultCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public static ResultCode fromCode(int code) {
    for (ResultCode c : ResultCode.values()) {
      if (c.getCode() == code) {
        return c;
      }
    }
    return INTERNAL_SERVER_ERROR;
  }
}
