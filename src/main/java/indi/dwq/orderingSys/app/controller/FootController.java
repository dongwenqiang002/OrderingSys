package indi.dwq.orderingSys.app.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.dwq.orderingSys.app.service.EateryService;
import indi.dwq.orderingSys.app.service.FoodService;
import indi.dwq.orderingSys.app.service.OrderService;
import indi.dwq.orderingSys.data.pojo.Eatery;
import indi.dwq.orderingSys.data.pojo.Food;
import indi.dwq.orderingSys.data.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author 董文强
 * @Time 2018/3/20 11:20
 */
@Controller
@RequestMapping("/food")
public class FootController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FootController.class);

    @Autowired
    private OrderService orderService;
    @Autowired
    private EateryService eateryService;
    @Autowired
    private FoodService foodService;

    /**
     * 添加商品
     */
    @PostMapping("/addFood")
    @ResponseBody
    public Boolean addFood(Food food, HttpSession session) {
        if (food == null) return false;
        User user = (User) session.getAttribute("user");
        Eatery eatery = eateryService.getEatery(user.getId());
        if (eatery == null) return false;
        food.setEateryId(eatery.getId());
        if(foodService.addFood(food)){
            return true;
        }
        return false;
    }

    /**
     * 删除
     */
    @GetMapping("/remove")
    @ResponseBody
    public Boolean addFood(Integer foodId, HttpSession session) {
        if (foodId == null) return false;
        User user = (User) session.getAttribute("user");
        Eatery eatery = eateryService.getEatery(user.getId());
        if (eatery == null) return false;
        if(foodService.removeFood(foodId,eatery.getId())){
            return true;
        }
        return false;
    }


    /**
     * 获取商店列表,
     *
     * @param isSubPage 是否只是获取table数据部分
     * @param pageNum   页码, 可以为null;
     */
    @RequestMapping("/eateryList")
    public ModelAndView footList(Integer pageNum, Boolean isSubPage) {
        LOGGER.info("请求商店列表页面!");
        ModelAndView modelAndView = new ModelAndView();
        if (isSubPage == null || isSubPage.equals(false)) {
            modelAndView.setViewName("/food/eateryList");
        } else {
            modelAndView.setViewName("/food/eateryList ::#eateryList");
        }
        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }
        Page page = PageHelper.startPage(pageNum, 12);
        modelAndView.addObject("eateryList", eateryService.getAll());
        if (pageNum > page.getPages()) {
            pageNum = page.getPages();
            PageHelper.startPage(pageNum, 12);
            modelAndView.addObject("eateryList", eateryService.getAll());
        }
        modelAndView.addObject("total", page.getTotal());
        modelAndView.addObject("pages", page.getPages());
        modelAndView.addObject("pageNum", pageNum);
        return modelAndView;
    }

    /**
     * 进入餐馆
     */
    @RequestMapping("/eatery/{id}")
    public ModelAndView intoEatery(@PathVariable("id") Integer id) {

        ModelAndView modelAndView = new ModelAndView("/food/foodList");
        modelAndView.addObject("foodList", foodService.getAll(id));
        return modelAndView;
    }

    /**
     * 下订单
     */
    @RequestMapping("/oreder")
    @ResponseBody
    public String order(String order, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Double price = orderService.orderDown(order, user);

        return price.toString();
    }


    /**
     * 获取指定商品销量
     */
    @GetMapping("/getCount")
    @ResponseBody
    public String getCount(Integer foodId) {
        LOGGER.info("查看商品 {} 的销量", foodId);
        Integer count = foodService.getCount(foodId);
        if (count == null) count = 0;
        return count + "份";
    }


    /**
     * 获取当前用户的订单列表
     */
    @GetMapping("/lookOrder")
    //@ResponseBody
    public ModelAndView lookOrder(HttpSession session) {
        LOGGER.info("根据用户ID查订单");
        User user = (User) session.getAttribute("user");
        if (user == null) throw new NullPointerException("用户未登录");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("onOrderList", orderService.onWayOrder(user.getId()));
        modelAndView.addObject("OrderList", orderService.lookOrder(user.getId()));
        modelAndView.setViewName("/user/userHome");
        return modelAndView;
    }
}
