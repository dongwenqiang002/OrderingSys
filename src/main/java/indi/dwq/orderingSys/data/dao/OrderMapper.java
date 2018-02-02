package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.Order;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper {

    int deleteByPrimaryKey(Integer id);


    int insert(Order record);


    int insertSelective(Order record);


    Order selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(Order record);


    int updateByPrimaryKey(Order record);
}