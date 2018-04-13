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
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.math.BigDecimal;
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
     * 下单
     * @param order [{商品ID:数量}{商品ID:数量}]
     * @param user  下单人, id 不能为空
     * @return 订单金额
     */
    @Transactional
    public Double orderDown(String order, User user) {


        JsonObject jsonObject = new JsonObject();
        //加入 下单用户ID
        jsonObject.append("foods", order).append("userId", user.getId());
        Order o = JsonUtil.jsonToObject2(jsonObject.toString(), Order.class);
        //设置下单时间
        o.setTime(new java.sql.Date(System.currentTimeMillis()));
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
            price = price*v.getCount();
            //price.s
            price =  new BigDecimal(price).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//四舍五入保留2位有效数字
            o.setPrice(o.getPrice() + price);
            orderMapper.insertOrderFood(v,orderId,price);
        });
        //throw  new NullPointerException("aa");
        LOGGER.info("id:{}   price:{}",orderId,o.getPrice());
        //设置订单的金额
        Double price =  new BigDecimal(o.getPrice()).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();//四舍五入保留2位有效数字
        orderMapper.setOrderPrice(orderId,price);

        return o.getPrice();
    }

    /**
     * 查看订单
     * */
    public List<Order> lookOrder(Integer id){
        return  orderMapper.selectByUserId(id);
    }


    /**
     * 查看当前正在途中的订单
     * */
    public List<Order> onWayOrder(Integer userId){
        return  orderMapper.selectByUserIdAndOnWay(userId);
    }

    /**
     * 查看对应订单的内容
     * */
    public List<Order.OrderFood> OrderFood(Integer orderId){
        return foodMapper.selectFoodSalesByOrderID(orderId);
    }

    /**
     * 商家接单
     * */
    public boolean orderUp(Integer orderId) {
        Order order = new Order();
        order.setId(orderId);
        order.setState(2);
        orderMapper.updateByPrimaryKeySelective(order);
        return true;
    }

    public Object lookOkOrder(Integer userId) {
        return foodMapper.selectonOKWayByUserid(userId);
    }
}
