package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);
}