package com.example.entity;

import java.util.List;
import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("customer")
@Data
public class Customer {
  private Integer customerId;
  private String customerName;
  private List<Order> orderList;
}
