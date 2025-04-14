package com.example.mapper;

import com.example.entity.Order;

public interface OrderMapper {
  Order selectOrderWithCustomer(Integer orderId);
}
