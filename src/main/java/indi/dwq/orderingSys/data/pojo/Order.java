package indi.dwq.orderingSys.data.pojo;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 订单类
 * */
public class Order {
    //订单ID
    private Integer id;
    //下单用户ID
    private Integer userId;
    //下单时间
    private Date time;
    //订单内容
    private List<OrderFood> foods;
    private Double price;
    //备注
    private String ps;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", 下单人ID=" + userId +
                ", 下单时间=" + time +
                ", 备注="+
                ", foods=" + Arrays.toString(foods.toArray()) +
                '}';
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<OrderFood> getFoods() {
        return foods;
    }

    public void setFoods(List<OrderFood> foods) {
        this.foods = foods;
    }

    //订单内容类
    public class OrderFood{
        @Override
        public String toString() {
            return "food{" +
                    "foodid=" + foodid +
                    ", count=" + count +
                    '}';
        }

        private Integer foodid;
        private Integer count;

        public Integer getFoodid() {
            return foodid;
        }

        public void setFoodid(Integer foodid) {
            this.foodid = foodid;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }
    }
}

