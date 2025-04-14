package com.example.mapper;

import com.example.entity.Customers;

/**
* @author 10952
* @description 针对表【customers】的数据库操作Mapper
* @createDate 2025-01-28 19:57:44
* @Entity com.example.entity.Customers
*/
public interface CustomersMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Customers record);

    int insertSelective(Customers record);

    Customers selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Customers record);

    int updateByPrimaryKey(Customers record);

}
