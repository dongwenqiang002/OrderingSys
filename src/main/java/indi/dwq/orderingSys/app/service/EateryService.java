package indi.dwq.orderingSys.app.service;


import indi.dwq.orderingSys.data.dao.*;
import indi.dwq.orderingSys.data.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EateryService {


    @Autowired
    private EateryMapper eateryMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserDetailMapper userDetailMapper;
    @Autowired
    private FoodMapper foodMapper;

    /**
     * 商家获取订单类列表
     *
     * @return List-> map-> {id(订单ID) ,ps(订单备注) ,time(下单时间),price(当前订单价钱)
     * state(订单状态) ,userName(下单人姓名) ,phone(下单人电话),
     * food(订单详细信息):{count(数量),price(价钱),name(商品名)}}
     */
    public List getOrderList(Integer userId) {
        Eatery eatery = eateryMapper.selectByUserId(userId);
        if (eatery == null) return null;
        List<Order> orderList = orderMapper.selectByEateryId(eatery.getId());
        List<Object> list = new LinkedList<>();

        //将各个订单按行加入到list中,使用map进行键值对的存储
        orderList.forEach(v -> {
            Map<String, Object> map = new HashMap<>();
            list.add(map);
            map.put("id", v.getId());//订单ID
           //订单备注
            if(v.getPs() == null){
                v.setPs("无");
            }
            map.put("ps", v.getPs());
            map.put("time", v.getTime());
            map.put("state", v.getState());

            //设置下单人信息
            Integer uId = v.getUserId();//下单人ID
            User user = userMapper.selectByPrimaryKey(uId);
            UserDetail userDetail = userDetailMapper.selectByPrimaryKey(user.getDetailId());
            if (userDetail.getSex().equals("男")) {
                map.put("userName", userDetail.getName().substring(0, 1) + "先生");
            } else if (userDetail.getSex().equals("女")) {
                map.put("userName", userDetail.getName().substring(0, 1) + "女士");
            } else {
                map.put("userName", userDetail.getName().substring(0, 1) + "**");
            }
            map.put("phone", userDetail.getPhone());
            List<Object> foods = new LinkedList<>();
            map.put("food", foods);
            AtomicReference<Double> price = new AtomicReference<>(0.0d);
            v.getFoods().forEach(f -> {
                Food food = foodMapper.selectByPrimaryKey(f.getFoodid());
                if (food.getEateryId().equals(eatery.getId())) {
                    Map<String, Object> foodMap = new HashMap<>();
                    foods.add(foodMap);
                    foodMap.put("count", f.getCount());
                    foodMap.put("price", f.getPrice());
                    price.updateAndGet(v1 -> ((double) (v1 + f.getPrice())));
                    foodMap.put("name", f.getFoodName());
                }
            });
            map.put("price",price.get());

        });
        return list;
    }

    public List<List> getAll() {
        List list = eateryMapper.getAll();
        //分行
        return branch(list, 4);
    }

    public Eatery getEatery(Integer userId) {

        return eateryMapper.selectByUserId(userId);
    }

    /**
     * 分行算法
     */
    public static List branch(List list, int n) {
        List lists = new LinkedList();

        int i;
        for (i = 0; i < list.size() - 4; i += 4) {
            lists.add(list.subList(i, i + 4));
        }
        lists.add(list.subList(i, list.size()));
        return lists;
    }
}
