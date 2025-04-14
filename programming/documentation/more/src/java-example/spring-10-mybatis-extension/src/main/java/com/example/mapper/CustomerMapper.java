package com.example.mapper;

public interface CustomerMapper {
  Customer selectCustomerWithOrderList(Integer customerId);
}
