package com.example.entity;

import java.util.List;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("customer")
@Data
public class Customer {
  private Integer customerId;
  private String customerName;

  // 体现的是“对多”的关系
  private List<Order> orderList;
}
