package indi.dwq.orderingSys.app.service;


import indi.dwq.orderingSys.data.dao.FoodMapper;
import indi.dwq.orderingSys.data.pojo.Food;
import indi.dwq.orderingSys.data.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FoodService {


    @Autowired
    private FoodMapper foodMapper;


    public List<Food> getAll(Integer eateryId) {

        return foodMapper.getSortFood(eateryId, "id");
    }

    public List<Food> getSortFood(Integer eateryId, String sortName) {
        if (sortName == null) {
            return foodMapper.getSortFood(eateryId, "id");
        }
        if (sortName.equals("count") || sortName.equals("销量"))
            return foodMapper.getSortFood(eateryId, "count desc");
        if (sortName.equals("name") || sortName.equals("名字") || sortName.equals("商品名"))
            return foodMapper.getSortFood(eateryId, "name");
        return foodMapper.getSortFood(eateryId, "id");
    }

    public Integer getCount(Integer foodId) {
        return foodMapper.selectCountByFoodId(foodId);
    }

    public Food getFood(Integer foodId) {
        return foodMapper.selectByPrimaryKey(foodId);
    }

    public Boolean addFood(Food food) {

        return foodMapper.insert(food) == 1;
    }

    public boolean removeFood(Integer foodId, Integer eateryId) {
        if (foodMapper.selectByPrimaryKey(foodId).getEateryId().equals(eateryId)) {
            if (foodMapper.removeFood(foodId) == 1)
                return true;
        }
        return false;
    }

    public List<Order.OrderFood> getFoodInfo(Integer eateryId,String sortName){
        if (sortName.equals("count") || sortName.equals("销量")) {
            return foodMapper.getFoodInfo(eateryId, "count");
        }else {
            return null;
        }
    }
}

