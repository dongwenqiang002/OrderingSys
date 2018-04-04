package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {


    int insertOrderFood(
            @Param("orderFood") Order.OrderFood orderFood,
            @Param("orderId") Integer orderId,
            @Param("price") Double price);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertOrder(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    int setOrderPrice(@Param("id") Integer orderId,@Param("price") Double price);
}