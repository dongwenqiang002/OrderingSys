package indi.dwq.orderingSys.data.dao;

import indi.dwq.orderingSys.data.pojo.Food;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FoodMapper {

    List<Food> getAll(Integer eateryId);

    int deleteByPrimaryKey(Integer id);

    int insert(Food record);

    int insertSelective(Food record);

    Food selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Food record);

    int updateByPrimaryKey(Food record);
}