package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.Food;
import indi.dwq.orderingSys.data.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoodMapper {

    int updateFoodImg(@Param("foodId") String foodID,@Param("img") String img);

    List<Order.OrderFood> selectFoodSalesByOrderID(Integer id);

    List<Food> getAll(Integer eateryId);

    List<Food> getSortFood(@Param("eateryId") Integer eateryId, @Param("sortName") String sortName);

    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);

    int  removeFood(Integer id);

    Integer selectCountByFoodId(Integer foodId);

    Order selectonOKWayByUserid(Integer userId);

    List<Order.OrderFood> getFoodInfo(@Param("eateryId") Integer eateryId, @Param("sortName") String sortName);
}