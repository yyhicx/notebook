package com.example.mapper;

import com.example.entity.Orders;

/**
* @author 10952
* @description 针对表【orders】的数据库操作Mapper
* @createDate 2025-01-28 19:57:44
* @Entity com.example.entity.Orders
*/
public interface OrdersMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

}
