package indi.dwq.orderingSys.app.controller;

import indi.dwq.orderingSys.app.service.EateryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 董文强
 * @Time 2018/3/20 11:20
 */
@Controller
@RequestMapping("/foot")
public class FootController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FootController.class);

    @Autowired
    private EateryService eateryService;

    @RequestMapping("/eateryList")
    public ModelAndView footList(Integer pageNum) {
        LOGGER.info("列表页面!");
        ModelAndView modelAndView = new ModelAndView("/foot/eateryList");
        modelAndView.addObject("eateryList",eateryService.getAll(pageNum));
        return modelAndView;
    }

    @RequestMapping("/eatery/{id}")
    public ModelAndView intoEatery(@PathVariable("id") Integer id){

        ModelAndView mv = new ModelAndView("foot/footList");

        mv.addObject("foot",null);

        return mv;
    }

}
