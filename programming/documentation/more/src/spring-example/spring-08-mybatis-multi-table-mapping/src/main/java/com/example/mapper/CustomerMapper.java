package com.example.mapper;

import com.example.entity.Customer;

public interface CustomerMapper {
  Customer selectCustomerWithOrderList(Integer customerId);
}
