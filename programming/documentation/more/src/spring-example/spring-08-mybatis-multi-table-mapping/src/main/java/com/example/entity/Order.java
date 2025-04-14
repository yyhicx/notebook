package com.example.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("order")
@Data
public class Order {
  private Integer orderId;
  private String orderName;
  private Integer customerId;

  // 体现的是“对一”的关系
  private Customer customer;
}
