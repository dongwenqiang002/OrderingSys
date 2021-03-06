package indi.dwq.orderingSys.app.service;


import indi.dwq.orderingSys.data.dao.*;
import indi.dwq.orderingSys.data.pojo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class EateryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EateryService.class);

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
    public List getOrderList(Integer eateryId) {
        /*Eatery eatery = eateryMapper.selectByUserId(userId);
        if (eatery == null) {
            return null;
        }
*/
        List<Order> orderList = orderMapper.selectByEateryId(eateryId);
        LOGGER.info("查询到订单数据 {} 条", orderList.size());
        List<Object> list = new LinkedList<>();

        //将各个订单按行加入到list中,使用map进行键值对的存储
        orderList.forEach(v -> {
            Map<String, Object> map = new HashMap<>();
            list.add(map);
            map.put("id", v.getId());//订单ID
            //订单备注
            if (v.getPs() == null) {
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
                if (food.getEateryId().equals(eateryId)) {
                    Map<String, Object> foodMap = new HashMap<>();
                    foods.add(foodMap);
                    foodMap.put("count", f.getCount());
                    foodMap.put("price", f.getPrice());
                    price.updateAndGet(v1 -> ((double) (v1 + f.getPrice())));
                    foodMap.put("name", f.getFoodName());
                }
            });
            map.put("price", price.get());

        });
        return list;
    }

    public List<List> getAll() {
        List list = eateryMapper.getAll();
        //分行
        return branch(list, 4);
    }

    public List<Map<String, Object>> getEattery() {
        List<Eatery> eateryList = eateryMapper.getAll();
        List<Map<String, Object>> list = new LinkedList();
        eateryList.forEach(v -> {
            Map map = new HashMap();
            list.add(map);
            map.put("imgUrl", v.getImgUrl());
            map.put("id", v.getId());
            map.put("eaterName", v.getEaterName());
            map.put("address", v.getAddress());
            map.put("des", v.getDes());
            map.put("name", "无");
            map.put("email", "无");
            map.put("iphone", "无");
            User user = userMapper.selectByPrimaryKey(v.getUserId());
            if (user == null) return;
            UserDetail userDetail = userDetailMapper.selectByPrimaryKey(user.getDetailId());
            if (userDetail == null) return;
            map.put("name", userDetail.getName());
            map.put("email", userDetail.getEmail());
            map.put("iphone", userDetail.getPhone());


        });
        return list;
    }

    public Eatery getEatery(Integer userId) {

        return eateryMapper.selectByUserId(userId);
    }

    public Boolean rePic(String name, Integer eateryId) {
        Eatery eatery = new Eatery();
        eatery.setImgUrl(name);
        eatery.setId(eateryId);
        return eateryMapper.updateByPrimaryKeySelective(eatery) == 1;
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

    public Object getEateryAllDeatil(Integer eateryid) {
        Eatery eatery = eateryMapper.selectByPrimaryKey(eateryid);
        Map map = new HashMap();
        map.put("imgUrl", eatery.getImgUrl());
        map.put("id", eatery.getId());
        map.put("eaterName", eatery.getEaterName());
        map.put("address", eatery.getAddress());
        map.put("des", eatery.getDes());
        map.put("name", "无");
        map.put("email", "无");
        map.put("iphone", "无");
        User user = userMapper.selectByPrimaryKey(eatery.getUserId());
        if (user == null) return map;
        UserDetail userDetail = userDetailMapper.selectByPrimaryKey(user.getDetailId());
        if (userDetail == null) return map;
        map.put("name", userDetail.getName());
        map.put("email", userDetail.getEmail());
        map.put("iphone", userDetail.getPhone());


        return map;
    }

    public boolean addEatery(Eatery eatery) {
        eateryMapper.insertSelective(eatery);
        return true;
    }

    public boolean updateEatery(Eatery eatery) {
        return eateryMapper.updateByPrimaryKeySelective(eatery)==1;
    }
}
