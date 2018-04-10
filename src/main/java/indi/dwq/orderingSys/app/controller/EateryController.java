package indi.dwq.orderingSys.app.controller;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import indi.dwq.orderingSys.app.service.EateryService;
import indi.dwq.orderingSys.app.service.FoodService;
import indi.dwq.orderingSys.data.dao.FoodMapper;
import indi.dwq.orderingSys.data.pojo.Eatery;
import indi.dwq.orderingSys.data.pojo.Food;
import indi.dwq.orderingSys.data.pojo.User;
import indi.dwq.orderingSys.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 餐馆controller
 */

@Controller
@RequestMapping("/eatery")
public class EateryController {

    @Autowired
    private EateryService eateryService;
    @Autowired
    private FoodService foodService;

    @RequestMapping("/list")
    public ModelAndView eateryList() {
        ModelAndView mv = new ModelAndView("/eatery/list");

        // mv.addObject();
        return null;
    }

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
    public ModelAndView homeHtml() {
        ModelAndView mv = new ModelAndView("/eatery/home");
        Eatery eatery = eateryService.getEatery(3);
        mv.addObject("eatery", eatery);
        return mv;
    }

    @RequestMapping("/food.html")
    public ModelAndView foodHtml(String sortName,Boolean isSubPage,Integer pageNum,HttpSession session ) {

        if (pageNum == null || pageNum <= 0) {
            pageNum = 1;
        }

        ModelAndView mv = new ModelAndView("/eatery/food"+((isSubPage!=null&&isSubPage)?" ::#main_table":""));
        User user = (User) session.getAttribute("user");
        Eatery eatery = eateryService.getEatery(user.getId());

        PageUtil.paging("foodList",mv,12,1,()->foodService.getSortFood(eatery.getId(),sortName));

        return mv;
    }




    @RequestMapping("/order.html")
    public ModelAndView orderHtml() {
        ModelAndView mv = new ModelAndView("/eatery/order");

        // mv.addObject();
        return mv;
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

}
