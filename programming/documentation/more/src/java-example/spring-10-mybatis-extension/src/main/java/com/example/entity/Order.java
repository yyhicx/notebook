package com.example.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("order")
@Data
public class Order {
  private Integer orderId;
  private String orderName;
  private Integer customerId;
  private Customer customer;
}
