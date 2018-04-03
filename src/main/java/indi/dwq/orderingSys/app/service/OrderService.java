package indi.dwq.orderingSys.app.service;

import indi.dwq.orderingSys.data.pojo.Order;
import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.util.JsonObject;
import indi.dwq.orderingSys.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author 董文强
 * @Time 2018/4/3 17:05
 */
@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);



    /**
     * @param order [{商品ID:数量}{商品ID:数量}]
     * @param user 下单人, id notnull
     * */
    public boolean orderDown(String order, User user) {
        StringBuilder completeOrder = new StringBuilder(100);

        JsonObject jsonObject = new JsonObject();
        jsonObject.append("foods",order).append("id",user.getId());
        Order o = JsonUtil.jsonToObject2(jsonObject.toString(),Order.class);
        LOGGER.info(jsonObject.toString());
        LOGGER.info(o.toString());
        return false;
    }
}
