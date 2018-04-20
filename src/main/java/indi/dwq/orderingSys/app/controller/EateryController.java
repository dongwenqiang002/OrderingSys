package indi.dwq.orderingSys.app.controller;


import indi.dwq.orderingSys.app.service.EateryService;
import indi.dwq.orderingSys.app.service.FileService;
import indi.dwq.orderingSys.app.service.FoodService;
import indi.dwq.orderingSys.app.service.OrderService;
import indi.dwq.orderingSys.data.pojo.Eatery;
import indi.dwq.orderingSys.data.pojo.Food;
import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import javax.servlet.http.HttpSession;

/**
 * 餐馆controller
 */

@Controller
@RequestMapping("/eatery")
public class EateryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EateryController.class);

    @Autowired
    private EateryService eateryService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private FileService fileService;


    @RequestMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("/eatery/index");

        // mv.addObject();
        return mv;
    }

    @RequestMapping("/index.html")
    public ModelAndView indexHtml() {
        ModelAndView mv = new ModelAndView("/eatery/index");

        // mv.addObject();
        return mv;
    }

    @RequestMapping("/home.html")
    public ModelAndView homeHtml(HttpSession session) {
        ModelAndView mv = new ModelAndView("/eatery/home");
        User user = (User) session.getAttribute("user");
        //获取当前用户的商铺信息
        Eatery eatery = eateryService.getEatery(user.getId());
        //获取订单列表
        List<Map> list = eateryService.getOrderList(user.getId());
        mv.addObject("number", list.size());

        //赋值操作不是线程安全的。若想不用锁来实现，可以用AtomicReference<V>这个类，实现对象引用的原子更新。
        AtomicReference<Double> price = new AtomicReference<>(0.0d);
        list.forEach(v -> {
            price.updateAndGet(v1 -> v1 + ((Double) v.get("price")));
        });

        mv.addObject("priceSum", new java.text.DecimalFormat("#.00").format(price.get()));
        mv.addObject("eatery", eatery);
        return mv;
    }

    @RequestMapping("/food.html")
    public ModelAndView foodHtml(String sortName, Boolean isSubPage, Integer pageNum, HttpSession session) {

        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }

        ModelAndView mv = new ModelAndView("/eatery/food" + ((isSubPage != null && isSubPage) ? " ::#main_table" : ""));
        User user = (User) session.getAttribute("user");
        Eatery eatery = eateryService.getEatery(user.getId());

        PageUtil.paging("foodList", mv, 5, pageNum, () -> foodService.getSortFood(eatery.getId(), sortName));

        return mv;
    }

    /**
     * 跳转商品模态框
     */
    @GetMapping("/foodAdd.html")
    public ModelAndView toFoodModel(Integer foodId) {
        ModelAndView mv = new ModelAndView("/eatery/foodAdd");
        if (foodId == null) return mv;
        Food food = foodService.getFood(foodId);
        mv.addObject("food", food);
        return mv;
    }

    /**
     * 查看订单列表
     * <th>订单编号</th>
     * <th>下单时间</th>
     * <th>价格</th>
     * <th>下单人姓名</th>
     * <th>联系电话</th>
     * <th>备注</th>
     * <th>当前状态</th>
     * <th>查看订单详情</th>  -> 订单内容展示
     */
    @RequestMapping("/order.html")
    public ModelAndView orderHtml(String pageNum, HttpSession session) {
        LOGGER.info("查询第 {} 页订单数据", pageNum);
        ModelAndView mv = new ModelAndView("/eatery/order");
        User user = (User) session.getAttribute("user");
        Eatery eatery = eateryService.getEatery(user.getId());
        PageUtil.paging("orderList", mv, 5, pageNum, () -> eateryService.getOrderList(eatery.getId()));
        return mv;
    }

    /**
     * 商家接单
     */
    @RequestMapping("/orderUp")
    @ResponseBody
    public boolean orderHtml(Integer orderId) {
        if (orderService.orderUp(orderId)) {
            return true;
        }
        return false;

    }

    @RequestMapping("/sale.html")
    public ModelAndView saleHtml() {
        ModelAndView mv = new ModelAndView("/eatery/sale");

        // mv.addObject();
        return mv;
    }

    @RequestMapping("/saleInfo.html")
    public ModelAndView saleInfoHtml() {
        ModelAndView mv = new ModelAndView("/eatery/saleInfo");

        // mv.addObject();
        return mv;
    }

    @RequestMapping("/reEateryPic")
    public ModelAndView reEateryPic(MultipartFile pic, HttpSession session) {
        String name = fileService.uploadImg(pic);
        User user = (User) session.getAttribute("user");
        Eatery eatery = eateryService.getEatery(user.getId());
        eatery.setImgUrl(name);
        if (eateryService.rePic(name, eatery.getId())) {
            ModelAndView mv = new ModelAndView("redirect:/eatery/");
            // mv.addObject();
            return mv;
        }else {
            return new ModelAndView("/error/error");
        }
    }
}
