package com.example.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Component;

@Component
public class MD5Util {
  private static final char[] hexChars = "0123456789abcdef".toCharArray();

  public static String encrypt(String str) {
    try {
      // 将字符串转换为字节数组，使用 MessageDigest 计算 MD5 值
      byte[] bytes = str.getBytes();
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(bytes);
      bytes = md.digest();

      // 将 MD5 值转换为 16 进制字符串
      int byteLength = bytes.length;
      char[] result = new char[byteLength * 2];
      int i = 0;
      for (byte b : bytes) {
        result[i++] = hexChars[b >> 4 & 0x0f];
        result[i++] = hexChars[b & 0x0f];
      }

      return new String(result);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new RuntimeException("MD5 encryption failed, " + e.getMessage());
    }
  }
}
