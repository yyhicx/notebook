package com.example.mapper;

import com.example.entity.Customer;
import java.util.List;

public interface CustomerMapper {
  Customer selectCustomerWithOrderList(Integer customerId);

  List<Customer> selectAll();
}
