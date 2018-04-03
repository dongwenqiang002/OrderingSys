package indi.dwq.orderingSys.data.pojo;


import java.util.Arrays;
import java.util.Date;

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
    private food[] foods;


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userId=" + userId +
                ", time=" + time +
                ", foods=" + Arrays.toString(foods) +
                '}';
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

    public food[] getFoods() {
        return foods;
    }

    public void setFoods(food[] foods) {
        this.foods = foods;
    }

    //订单内容类
    public class food{
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

