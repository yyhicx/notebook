package com.example.mapper;

import com.example.entity.Order;
import java.util.List;

public interface OrderMapper {
  Order selectOrderWithCustomer(Integer orderId);

  List<Order> selectAll();
}
