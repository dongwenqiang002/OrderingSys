package indi.dwq.orderingSys.app.service;

import indi.dwq.orderingSys.data.dao.FoodMapper;
import indi.dwq.orderingSys.data.dao.OrderMapper;
import indi.dwq.orderingSys.data.pojo.Order;
import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.util.JsonObject;
import indi.dwq.orderingSys.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 董文强
 * @Time 2018/4/3 17:05
 */
@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    FoodMapper foodMapper;
    @Autowired
    OrderMapper orderMapper;

    /**
     * @param order [{商品ID:数量}{商品ID:数量}]
     * @param user  下单人, id 不能为空
     * @return 订单金额
     */
    public Double orderDown(String order, User user) {


        JsonObject jsonObject = new JsonObject();
        //加入 下单用户ID
        jsonObject.append("foods", order).append("userId", user.getId());
        Order o = JsonUtil.jsonToObject2(jsonObject.toString(), Order.class);
        //设置下单时间
        o.setTime(new Date());
        //加入三元订单费费
        o.setPrice(3.0d);
        //先插入订单
        LOGGER.info("插入订单{}", o.toString());
        orderMapper.insertOrder(o);

        //获取插入订单的ID
        Integer orderId = o.getId();

        //将商品加入到订单下
        o.getFoods().forEach(v -> {
            Double price = Double.valueOf(foodMapper.selectByPrimaryKey(v.getFoodid()).getPrice());
            o.setPrice(o.getPrice() + price);
            orderMapper.insertOrderFood(v,orderId,price);
        });

        //设置订单的金额
        orderMapper.setOrderPrice(orderId,o.getPrice());

        return o.getPrice();
    }
}
